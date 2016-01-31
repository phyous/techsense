package com.phyous.techsense;

import com.phyous.techsense.model.ImmutableHeadline;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    
    public static void main(String[] args) {
        
        String url = "http://www.techmeme.com/";

        try {
            Document doc = Jsoup.connect(url).get();
            Elements topLinks = doc.select("#topcol1 .clus");
            
            topLinks.stream()
                .map(x -> {
                    Element titleBar = x.select(".shrtbl cite").first();
                    Element mainStory = x.select(".itc1 .itc2 .item .ii").first();
                    
                    String reporter = titleBar.ownText().split(" / ")[0];
                    String source = titleBar.select("a").first().ownText();
                    String title = mainStory.select("strong a").first().ownText();
                    String summary = mainStory.ownText();
                    
                    return ImmutableHeadline.builder()
                        .reporter(reporter)
                        .source(source)
                        .title(title)
                        .summary(summary)
                        .build();
                })
                .forEach(System.out::println);
            
        } catch (Exception e) {
            System.out.println(String.format("Error processing url: %s", e.getMessage()));
        }
    }
}
