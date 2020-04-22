package utilsTools.module;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;


/*@Setter
@Getter*/
@Data
public class GraphNode {

    //@Value(0L)
    private Long id = 0L;
    //@Value("1")
    private String name = "1";
}
