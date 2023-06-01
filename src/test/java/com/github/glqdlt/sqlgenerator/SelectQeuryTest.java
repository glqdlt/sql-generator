package com.github.glqdlt.sqlgenerator;


import org.junit.Assert;
import org.junit.Test;

public class SelectQeuryTest {

    @Test
    public void canMakeSelectQuery() {
        SimpleSelectQueryGenerator query
                = new SimpleSelectQueryGenerator("table_abc");
        Assert.assertEquals("select * from table_abc", query.generate());
    }

    @Test
    public void canMakeSelectQuery2() {
        SimpleSelectQueryGenerator query
                = new SimpleSelectQueryGenerator("table_abc");
        query.offset("limit 10");
        Assert.assertEquals("select * from table_abc limit 10", query.generate());
    }

    @Test
    public void canMakeSelectQuery3() {
        SimpleSelectQueryGenerator query
                = new SimpleSelectQueryGenerator("table_abc");
        query.command("count(*)");
        query.order("order by column1 desc");
        query.criteria("column1 = 'a'", "column2 = 'b'");
        query.offset("limit 10,100");
        Assert.assertEquals("select count(*) from table_abc where column1 = 'a' and column2 = 'b' order by column1 desc limit 10,100", query.generate());
    }
}