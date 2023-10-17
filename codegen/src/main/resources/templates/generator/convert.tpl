package #(packageConfig.entityPackage);

#for(importClass : imports)
import #(importClass);
#end

/**
 * #(table.getEntityJavaFileName()) <=> #(table.buildEntityClassName()) 转化工具。
 *
 * @author #(javadocConfig.getAuthor())
 * @date #(javadocConfig.getSince())
 */
@Mapper(componentModel = "spring")
public interface #(table.buildEntityClassName())Convert extends ConvertTemplate<#(table.getEntityJavaFileName()), #(table.buildEntityClassName())> {
}
