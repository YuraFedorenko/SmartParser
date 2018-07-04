package com.agileengine.parser.SmartParser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parser {
	private static Logger LOGGER = LoggerFactory.getLogger(Parser.class);
	private static Elements elements;
	private static String CHARSET_NAME = "utf8";

	public static Optional<Elements> findElementsByQuery(File htmlFile, String cssQuery) {
		try {
			Document doc = Jsoup.parse(htmlFile, CHARSET_NAME, htmlFile.getAbsolutePath());

			elements = doc.select(cssQuery);

			for (Element el : elements) {
				System.out.print(el.tagName() + " < ");
			}
			for (Element el : elements.parents()) {
				System.out.print(el.tagName() + " < ");
			}
			return Optional.of(elements);

		} catch (IOException e) {
			LOGGER.error("Error reading [{}] file", htmlFile.getAbsolutePath(), e);
			return Optional.empty();
		}
	}

	public static Optional<Element> findElementById(File htmlFile, String targetElementId) {
		try {
			Document doc = Jsoup.parse(htmlFile, CHARSET_NAME, htmlFile.getAbsolutePath());

			return Optional.of(doc.getElementById(targetElementId));

		} catch (IOException e) {
			LOGGER.error("Error reading [{}] file", htmlFile.getAbsolutePath(), e);
			return Optional.empty();
		}
	}
}
