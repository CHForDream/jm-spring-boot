package com.game.utils;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class AnnotationUtils {
	public static List<Class<?>> findBeanClasses(String packageName, Class<? extends Annotation> annotation) throws ClassNotFoundException {
		List<Class<?>> classes = new LinkedList<Class<?>>();

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(annotation));

		for (BeanDefinition def : scanner.findCandidateComponents(packageName)) {
			classes.add(Class.forName(def.getBeanClassName()));
		}

		return classes;
	}
}
