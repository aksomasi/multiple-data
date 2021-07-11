package com.vihantech;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Shravani {

	static List<Attachment> attachments = new ArrayList<>();
	static List<Comment> comments = new ArrayList<>();

	public static void main(String[] args) {
		lodaData();

		List<Comment> attestation = filterData();
		attestation.forEach(at -> System.out.println(at.getFileInfo().size()));


	}

	public static List<Comment> filterData() {
		// comments means attestations  in ur project
		// attachments means retrive attachments in ur project
		comments.forEach(val -> {
			val.setFileInfo(attachments.stream()
					.filter(va -> va.getReportyKey().equals("1") && va.getReportyType().equals("COMMENT"))
					.collect(Collectors.toList()));
		});
		return comments.stream().filter(distinctByKey(Comment::getComment)).collect(Collectors.toList());

	}

	// predicate to filter the duplicates by the given key extractor.
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> uniqueMap = new ConcurrentHashMap<>();
		return t -> uniqueMap.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static void lodaData() {
		attachments.add(new Attachment("1", "COMMENT", "1"));
		attachments.add(new Attachment("1", "COMMENT", "2"));
		attachments.add(new Attachment("1", "", "3"));
		attachments.add(new Attachment("2", "", "12"));
		attachments.add(new Attachment("3", "COMMENT", "133"));
		attachments.add(new Attachment("4", "", "156"));
		attachments.add(new Attachment("5", "COMMENT", "145"));
		attachments.add(new Attachment("6", "COMMENT", "111"));
		attachments.add(new Attachment("7", "COMMENT", "123"));

		comments.add(new Comment("1", "new save", "1", "aks.pdf"));
		comments.add(new Comment("1", "new save", "2", "aks.pdf"));
		comments.add(new Comment("1", "new save", "3", "aks.pdf"));

	}
}
