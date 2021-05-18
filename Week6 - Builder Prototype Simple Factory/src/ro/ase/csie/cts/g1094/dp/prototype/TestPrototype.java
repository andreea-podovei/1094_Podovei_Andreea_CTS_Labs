package ro.ase.csie.cts.g1094.dp.prototype;

public class TestPrototype {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		Tree3DModel tree1 = new Tree3DModel("Green", 200);
		//Tree3DModel tree2 = new Tree3DModel("Red", 150);
		//Tree3DModel tree3 = new Tree3DModel("Green", 200);

		//shallow copy
		//is just another reference pointing to tree1
		//Tree3DModel tree4 = tree1;
		
		Tree3DModel tree4 = (Tree3DModel) tree1.clone();
		Tree3DModel tree5 = (Tree3DModel) tree1.clone();
		Tree3DModel tree6 = (Tree3DModel) tree1.clone();
		
		if(tree1 != tree4)
			System.out.println("They are different");
		else System.out.println("They are the same");
		System.out.println("The end");
	}

}
