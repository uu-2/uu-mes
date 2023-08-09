package cn.yoyo.codegen.generator;

import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;
import cn.yoyo.components.biz.CrudBaseRepo;
import cn.yoyo.components.biz.infra.repository.CrudBaseRepoMybatisImpl;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DDDRepositoryImplGenerator implements IGenerator {
    private String templatePath = "/templates/generator/repository-impl.tpl";

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
        String dddDomainPackage = packageConfig.getBasePackage() + ".domain";
        String dddInfraPackage = packageConfig.getBasePackage() + ".infra";
        String dddRepoImplPackage = dddInfraPackage + ".repository";

        String entityPackagePath = dddRepoImplPackage.replace(".", "/");
        File entityJavaFile = new File(packageConfig.getSourceDir(), entityPackagePath + "/" +
                table.getEntityJavaFileName() + "RepositoryImpl.java");


        if (entityJavaFile.exists() && !entityConfig.isOverwriteEnable()) {
            return;
        }

        /**
         * import cn.yoyo.module.demo.infra.repository.mybatis.dto.TOperLog;
         * import cn.yoyo.module.demo.infra.repository.mybatis.dto.TOperLogConvert;
         * import cn.yoyo.module.demo.infra.repository.mybatis.mapper.TOperLogMapper;
         */
        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("entityConfig", entityConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());
        params.put("imports", new String[]{
                Component.class.getName(),
                CrudBaseRepoMybatisImpl.class.getName(),
                dddDomainPackage + ".entity." + table.getEntityJavaFileName(),
                dddDomainPackage + ".points." + table.getEntityJavaFileName() + "Repository",
                dddInfraPackage + ".repository.mybatis.dto." + table.buildEntityClassName(),
                dddInfraPackage + ".repository.mybatis.dto." + table.buildEntityClassName()+"Convert",
                dddInfraPackage + ".repository.mybatis.mapper." + table.buildEntityClassName()+"Mapper",
        });
        params.put("dddRepoImplPackage", dddRepoImplPackage);

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, entityJavaFile);
    }
}
