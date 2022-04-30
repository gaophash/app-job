package com.gaop.appjob;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * Description : MysqlGenerator. <br />
 * Create Time : 2019年7月22日 上午11:08:14 <br />
 * Copyright : Copyright (c) 2010 - 2019 All rights reserved. <br />
 * 
 * @author Yanshaodong
 * @version 1.0
 */
public class MysqlGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/code-generator/");
        gc.setAuthor("GaoPeng");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/app-job?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setTypeConvert(new MySqlTypeConvert() {
        	@Override
        	public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        		if (fieldType.toLowerCase().equalsIgnoreCase("timestamp")) {
        			return DbColumnType.DATE;
        		}
        		if (fieldType.toLowerCase().startsWith("decimal")) {
        			return DbColumnType.DOUBLE;
        		}
        		return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
	        	}
	        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.gaop.appjob");
        mpg.setPackageInfo(pc);
       
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.execute();
        System.out.println("code-generator执行完毕");
    }

}
