package com.agileengine.parser.SmartParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RootClassParser {
	 private static Logger LOGGER = LoggerFactory.getLogger(RootClassParser.class);
	 
	public static void main(String[] args) throws IOException {
		
		// String sampleResourcePath = args[0];
		// for selecting by css query
		
		String cssQuery = "div[class*=\"panel\"] a[onclick*=\"javascript:window.ok\"]";
		for (int i = 0; i < args.length; i++) {

			Optional<Elements> elementsOpt = Parser.findElementsByQuery(new File(args[i]), cssQuery);

			Optional<List<String>> elementsAttrsOpts = elementsOpt.map(buttons -> {
				
				List<String> stringifiedAttrs = new ArrayList<>();

				buttons.iterator().forEachRemaining(button ->
                stringifiedAttrs.add(
                        button.attributes().asList().stream()
                                .map(attr -> attr.getKey() + " = " + attr.getValue())
                                .collect(Collectors.joining(", "))));
				return stringifiedAttrs;

			});

			// for selecting by id
//			 String targetElementId = "make-everything-ok-button";
//			 Optional<Element> buttonOpt = Parser.findElementById(new File(args[i]),
//			 targetElementId);
//			
//			 Optional<String> stringifiedAttributesOpt = buttonOpt.map(button ->
//			 button.attributes().asList().stream()
//			 .map(attr -> attr.getKey() + " = " + attr.getValue())
//			 .collect(Collectors.joining(", "))
//			 );

			System.out.println();
			System.out.println(elementsAttrsOpts.get());
		}
	}
}
