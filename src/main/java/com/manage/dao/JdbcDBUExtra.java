package com.manage.dao;

import jmind.core.jdbc.BeanJdbc;
import jmind.core.manager.JdbcManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcDBUExtra<E> extends BeanJdbc<E> {
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public JdbcTemplate getJdbc(int source) {
        return JdbcManager.getInstance().getResource("zwsc.extra");
    }
    
	public Number insertAndReturnId(SimpleJdbcInsert jdbcInsert, Object obj) {
		jdbcInsert.setGeneratedKeyName("id");
		return super.insertAndReturnKey(jdbcInsert, obj);
	}

}
