/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

/**
 *
 * @author Guilherme
 */
@Resource
public class IndexController {

    private final Result result;

    public IndexController(Result result) {
        this.result = result;
    }
    
    @Path("/")
    public void index(){ }
    
    @Path("/cadastro")
    public void cadastroIndex(){ }
    
    
    
}
