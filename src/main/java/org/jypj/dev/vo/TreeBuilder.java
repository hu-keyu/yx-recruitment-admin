package org.jypj.dev.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class TreeBuilder {
	@SuppressWarnings("unchecked")
    public List<Node> buildListToTree(List<Node> dirs) {
        List<Node> roots = findRoots(dirs);
        List<Node> notRoots = (List<Node>) CollectionUtils
                .subtract(dirs, roots);
        for (Node root : roots) {
            root.setMenuSecond(findChildren(root, notRoots));
        }
        return roots;
    }
    public List<Node> findRoots(List<Node> allNodes) {
        List<Node> results = new ArrayList<Node>();
        for (Node node : allNodes) {
            boolean isRoot = true;
            for (Node comparedOne : allNodes) {
                if (node.getParentId()!=null&&node.getParentId().equals(comparedOne.getId())) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                results.add(node);
            }
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    private List<Node> findChildren(Node root, List<Node> allNodes) {
        List<Node> children = new ArrayList<Node>();
        for (Node comparedOne : allNodes) {
            if (comparedOne.getParentId().equals(root.getId())) {
                comparedOne.setParent(root);
                children.add(comparedOne);
            }
        }
        List<Node> notChildren = (List<Node>) CollectionUtils.subtract(allNodes, children);
        for (Node child : children) {
            List<Node> tmpChildren = findChildren(child, notChildren);
            child.setMenuSecond(tmpChildren);
        }
        return children;
    }

}
