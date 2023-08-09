package #(dddRestPackage);

#for(importClass : imports)
import #(importClass);
#end

/**
 * #(table.getEntityJavaFileName()) 业务服务。
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
@RestController
@RequestMapping("#(apiUrl)")
@Tag(name = "#(table.getComment())")
public class #(table.getEntityJavaFileName())Api extends CrudBaseApi<#(table.getEntityJavaFileName()), #(table.getEntityJavaFileName())Biz> {

    public #(table.getEntityJavaFileName())Api(#(table.getEntityJavaFileName())Biz crudBiz) {
        super(crudBiz);
    }
}
