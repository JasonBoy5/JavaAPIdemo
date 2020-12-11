package com.jason.util.BinaryTree;

import java.util.Arrays;

class Person implements Comparable<Person>{
	private String name;
	private int age;
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]\n";
	}

	@Override
	public int compareTo(Person o) {
		return this.age - o.age;//ASC
//		return o.age - this.age;//DEC
	}
}

class BinaryTree<T extends Comparable<T>>{
	
	private class Node{
		private Comparable<T> data;
		private Node parent;
		private Node left;
		private Node right;
		public Node(Comparable<T> data) {
			this.data = data;
		}
		public void addNode(Node newNode) {
			if(newNode.data.compareTo((T)this.data) <= 0) {
				if(this.left == null) {
					this.left = newNode;
					newNode.parent = this;
				}else {
					this.left.addNode(newNode);
				}
			}else {
				if(this.right == null) {
					this.right = newNode;
					newNode.parent = this;
				}else {
					this.right.addNode(newNode);
				}
			}
			
		}
		public void toArrayNode() {
			if(this.left != null) {
				this.left.toArrayNode();
			}
			BinaryTree.this.returnData[BinaryTree.this.foot++] = this.data;
			if(this.right != null) {
				this.right.toArrayNode();
			}
		}
		public boolean containsNode(Comparable<T> data) {
			if(data.compareTo((T)this.data) == 0) {
				return true;
			}else if(data.compareTo((T)this.data)< 0) {
				if(this.left != null) {
					return this.left.containsNode(data);
				}else {
					return false;
				}
			}else {
				if(this.right != null) {
					return this.right.containsNode(data);
				}else {
					return false;
				}
			}
		}
		public Node getRemoveNode(Comparable<T> data) {
			if(data.compareTo((T)this.data) == 0) {
				return this;
			}else if(data.compareTo((T)this.data)< 0) {
				if(this.left != null) {
					return this.left.getRemoveNode(data);
				}else {
					return null;
				}
			}else {
				if(this.right != null) {
					return this.right.getRemoveNode(data);
				}else {
					return null;
				}
			}
		}
		
	}
	
	private Node root;
	private int count = 0;
	private Object[] returnData;
	private int foot = 0;
	public void add(Comparable<T> data) {
		if(data == null) {
			throw new NullPointerException("保存数据不允许为空");
		}
		Node newNode = new Node(data);
		if(this.root == null) {
			this.root = newNode;
		}else {
			this.root.addNode(newNode);
		}
		this.count++;
	}
	
	public Object[]  toArray(){
		if(this.count == 0) {
			return null;
		}
		this.returnData = new Object[this.count];
		this.foot = 0;
		this.root.toArrayNode();
		return this.returnData;	
	}
	public void remove(Comparable<T> data) {
		Node removeNode = this.root.getRemoveNode(data);
		if(null != removeNode) {
			if(null == removeNode.left && null == removeNode.right) {
				removeNode.parent.left = null;
				removeNode.parent.right = null;
				removeNode.parent = null;
			}else if(null != removeNode.left && null == removeNode.right) {
				removeNode.parent.left = removeNode.left;
				removeNode.left.parent = removeNode.parent;
			}else if(null == removeNode.left && null != removeNode.right) {
				removeNode.parent.right = removeNode.right;
				removeNode.right.parent = removeNode.parent;
			}else {//两边都有节点，则将右边节点中最左边的节点找到，改变其指向
				Node moveNode = removeNode.right;
				while(null != moveNode.left) {
					moveNode = moveNode.left;
				}
				moveNode.parent.left = null;
				moveNode.parent = removeNode.parent;
				moveNode.right = removeNode.right;
			}
		}
		this.count--;
	}
}

public class BinaryTreeTest {

	public static void main(String[] args) {
		BinaryTree<Person> tree = new BinaryTree<Person>();
		tree.add(new Person("A-80",80));
		tree.add(new Person("A-50",50));
		tree.add(new Person("A-60",60));
		tree.add(new Person("A-30",30));
		tree.add(new Person("A-90",90));
		tree.add(new Person("A-35",35));
		tree.add(new Person("A-55",55));
		tree.add(new Person("A-70",70));
		tree.add(new Person("A-85",85));
		tree.add(new Person("A-95",95));
		System.out.println(Arrays.toString(tree.toArray()));
		tree.remove(new Person("A-30",30));
		System.out.println(Arrays.toString(tree.toArray()));
	}
}
