package singular.structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew Michel on 12/18/2017.
 */
public interface TreeTemplate<E>
{
    public List<E> inorderTraversal();

    public List<E> preorderTraversal();

    public List<E> postorderTraversal();
}
