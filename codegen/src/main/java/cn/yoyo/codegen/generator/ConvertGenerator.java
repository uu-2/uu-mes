package cn.yoyo.codegen.generator;

import cn.hutool.core.collection.ListUtil;
import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;
import cn.yoyo.components.biz.convert.ConvertTemplate;
import org.mapstruct.Mapper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConvertGenerator implements IGenerator {
    private String templatePath = "/templates/generator/convert.tpl";

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    @Override
    public void setTemplatePath(String s) {
        this.templatePath = s;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isEntityGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        EntityConfig entityConfig = globalConfig.getEntityConfig();

        String entityPackagePath = packageConfig.getEntityPackage().replace(".", "/");
        File entityJavaFile = new File(packageConfig.getSourceDir(), entityPackagePath + "/" +
                table.buildEntityClassName() + "Convert.java");


        if (entityJavaFile.exists() && !entityConfig.isOverwriteEnable()) {
            return;
        }

        Map<String, Object> params = new HashMap<>(4);
        params.put("imports", ListUtil.of(
                ConvertTemplate.class.getName(),
                Mapper.class.getName(),
                packageConfig.getBasePackage()+".domain.entity."+table.getEntityJavaFileName()
        ));

        params.put("table", table);
        params.put("entityConfig", entityConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, entityJavaFile);
    }
}
