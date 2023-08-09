package #(dddRepoPackage);

#for(importClass : imports)
import #(importClass);
#end

/**
 * #(table.getEntityJavaFileName()) 存储。
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
public interface #(table.getEntityJavaFileName())Repository extends CrudBaseRepo<#(table.getEntityJavaFileName())> {

}
