package #(dddBizPackage);

#for(importClass : imports)
import #(importClass);
#end

/**
 * #(table.getEntityJavaFileName()) 业务服务。
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
@Component
public class #(table.getEntityJavaFileName())Biz extends CrudBaseBiz<#(table.getEntityJavaFileName()), Long> {

    public #(table.getEntityJavaFileName())Biz(#(table.getEntityJavaFileName())Repository repo) {
        super(repo);
    }
}
