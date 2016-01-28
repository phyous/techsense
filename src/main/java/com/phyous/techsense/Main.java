package com.phyous.techsense;

import com.phyous.techsense.model.ImmutableHeadline;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by pyoussef on 1/27/16.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Hello world!");
        
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
                    //String title = mainStory.select(".L3 a").first().ownText();
                    
                    return ImmutableHeadline.builder()
                        .reporter(reporter)
                        .source(source)
                    //    .title(title)
                        .build();
                })
                .forEach(System.out::println);
            
        } catch (Exception e) {
            System.out.println(String.format("Error processing url: %s", e.getMessage()));
        }
    }
}
