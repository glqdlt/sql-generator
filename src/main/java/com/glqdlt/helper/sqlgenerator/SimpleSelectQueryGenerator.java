package com.glqdlt.helper.sqlgenerator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author glqdlt
 */
public class SimpleSelectQueryGenerator implements QueryGenerator {

    private String command = "*";

    private final String tableName;

    private String order;

    private String offset;

    private List<String> internalCriteria = new LinkedList<>();

    public SimpleSelectQueryGenerator(String tableName) {
        this.tableName = tableName;
    }

    public SimpleSelectQueryGenerator criteria(String... criteria) {
        this.internalCriteria = Arrays.asList(criteria);
        return this;
    }

    public SimpleSelectQueryGenerator order(String order) {
        this.order = order;
        return this;
    }

    public SimpleSelectQueryGenerator offset(String offset) {
        this.offset = offset;
        return this;
    }


    public SimpleSelectQueryGenerator command(String command) {
        this.command = command;
        return this;
    }


    public String generate() {

        List<String> part = new LinkedList<>();
        part.add("select");
        part.add(command);
        part.add("from");
        part.add(tableName);

        internalCriteria.stream()
                .reduce((ac, x) -> ac + " and " + x)
                .map(x -> "where " + x)
                .ifPresent(part::add);

        Optional.ofNullable(order).ifPresent(part::add);

        Optional.ofNullable(offset).ifPresent(part::add);

        return part.stream().reduce((ac, x) -> ac + " " + x).orElse("");

    }

}
