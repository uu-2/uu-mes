package #(dddRepoImplPackage);

#for(importClass : imports)
import #(importClass);
#end

/**
 * #(table.getEntityJavaFileName()) 存储层实现。
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
@Component
public class #(table.getEntityJavaFileName())RepositoryImpl extends CrudBaseRepoMybatisImpl<#(table.buildEntityClassName()),#(table.getEntityJavaFileName())>
    implements #(table.getEntityJavaFileName())Repository {

    protected #(table.getEntityJavaFileName())RepositoryImpl(#(table.buildEntityClassName())Mapper mapper, #(table.buildEntityClassName())Convert converter) {
        super(mapper, converter);
    }
}
