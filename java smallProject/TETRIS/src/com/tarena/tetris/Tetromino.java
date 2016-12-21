package com.tarena.tetris;

import java.util.Arrays;
import java.util.Random;
//�κ����Ͷ��̳���Object == ɶ���Ƕ��� 
//==Ψ������!== һ�н�
public abstract class Tetromino 
	/*extends Object*/ {
	protected Cell[] cells = new Cell[4];
	protected State[] states;
	protected int index = 10000;
	protected class State{
		int row0,col0,row1,col1,
			row2,col2,row3,col3;
		public State(int row0, int col0, int row1, int col1, int row2, int col2,
				int row3, int col3) {
			this.row0 = row0;	this.col0 = col0;
			this.row1 = row1;	this.col1 = col1;
			this.row2 = row2;   this.col2 = col2;
			this.row3 = row3;	this.col3 = col3;
		}
	}
	
	/** ���"����"7�ַ���֮һ, �ǹ�������
	 * ��������: ��������(����)һ������ķ���
	 *     ��װ�˸��ӵĴ�������. ʹ�÷���.
	 *     ���ڴ���������̸��ӵ����.
	 **/
	public static Tetromino randomOne(){
		Random random = new Random();
		int type = random.nextInt(7);//[0,7)
		switch(type){
		case 0: return new T();
		case 1: return new I();
		case 2: return new S();
		case 3: return new Z();
		case 4: return new J();
		case 5: return new L();
		case 6: return new O();
		}
		return null;
	}
	/** Ϊ�˱��ڲ��Եķ��� */
	public String toString(){
		return Arrays.toString(cells);
		//return "["+cells[0].toString()+","+
		//cells[1].toString()+","+
		//cells[2].toString()+","+
		//cells[3].toString()+"]";
	}
	
	/** ��ǰ��������4�񷽿����������ƶ� */
	public void moveLeft(){
		cells[0].moveLeft();
		cells[1].moveLeft();
		cells[2].moveLeft();
		cells[3].moveLeft();
	}
	public void moveRight(){
		for(int i=0; i<cells.length; i++){
			this.cells[i].moveRight();
		}
	}
	public void softDrop(){
		for(int i=0; i<cells.length; i++){
			cells[i].softDrop();
		}
	}
	//��Tetromino �������ת�㷨
	/** ������ת�㷨 */
	public void rotateRight(){
		//����index++
		//��ȡSn(4������ 0 1 2 3)
		//��ȡ��ǰ����
		//����0 ���� ����
		//����1 �����б�Ϊ: �� + Sn[1]
		//����2 �����б�Ϊ: �� + Sn[2]
		//����3 �����б�Ϊ: �� + Sn[3]
		index++;
		//System.out.println(
		//		"index:"+(index % states.length));
		State s = states[index % states.length];
		Cell o = cells[0];
		int row = o.getRow();
		int col = o.getCol();
		//System.out.println(this);
		//System.out.println("cells[1]:"+cells[1]);
		//System.out.println("s:"+s);
		cells[1].setRow(row + s.row1);
		cells[1].setCol(col + s.col1);
		cells[2].setRow(row + s.row2);
		cells[2].setCol(col + s.col2);
		cells[3].setRow(row + s.row3);
		cells[3].setCol(col + s.col3);
	}
	public void rotateLeft(){
		index--;
		State s = states[index % states.length];
		Cell o = cells[0];
		int row = o.getRow();
		int col = o.getCol();
		cells[1].setRow(row + s.row1);
		cells[1].setCol(col + s.col1);
		cells[2].setRow(row + s.row2);
		cells[2].setCol(col + s.col2);
		cells[3].setRow(row + s.row3);
		cells[3].setCol(col + s.col3);
	}
}
class T extends Tetromino{
	public T() {
		cells[0]=new Cell(0,4,Tetris.T);
		cells[1]=new Cell(0,3,Tetris.T);
		cells[2]=new Cell(0,5,Tetris.T);
		cells[3]=new Cell(1,4,Tetris.T);
		states = new State[4];
		states[0]=new State(0,0,0,-1,0,1,1,0);//S0
		states[1]=new State(0,0,-1,0,1,0,0,-1);//S1
		states[2]=new State(0,0,0,1,0,-1,-1,0);//S2
		states[3]=new State(0,0,1,0,-1,0,0,1);//S3
	}
}
class I extends Tetromino{
	public I() {
		cells[0] = new Cell(0, 4, Tetris.I);
		cells[1] = new Cell(0, 3, Tetris.I);
		cells[2] = new Cell(0, 5, Tetris.I);
		cells[3] = new Cell(0, 6, Tetris.I);
		states = new State[2];
		states[0] = new State(0,0,0,-1,0,1,0,2);
		states[1] = new State(0,0,-1,0,1,0,2,0);
	}
}
class S extends Tetromino{
	public S() {
		cells[0] = new Cell(1, 4, Tetris.S);
		cells[1] = new Cell(0, 3, Tetris.S);
		cells[2] = new Cell(0, 4, Tetris.S);
		cells[3] = new Cell(1, 5, Tetris.S);
		states = new State[2];
		states[0] = new State(0,0,0,-1,-1,0,-1,1);
		states[1] = new State(0,0,-1,0,0,1,1,1);
	}
}
class Z extends Tetromino{
	public Z() {
		cells[0] = new Cell(1, 4, Tetris.Z);
		cells[1] = new Cell(0, 3, Tetris.Z);
		cells[2] = new Cell(0, 4, Tetris.Z);
		cells[3] = new Cell(1, 5, Tetris.Z);
		states = new State[2];
		states[0] = new State(0,0,-1,-1,-1,0,0,1);
		states[1] = new State(0,0,-1,1,0,1,1,0);
	}
}
class O extends Tetromino{
	public O() {
		cells[0] = new Cell(0, 4, Tetris.O);
		cells[1] = new Cell(0, 5, Tetris.O);
		cells[2] = new Cell(1, 4, Tetris.O);
		cells[3] = new Cell(1, 5, Tetris.O);
		states = new State[2];
		states[0] = new State(0,0,0,1,1,0,1,1);
		states[1] = new State(0,0,0,1,1,0,1,1);
	}
}
class L extends Tetromino{
	public L() {
		cells[0] = new Cell(0, 4, Tetris.L);
		cells[1] = new Cell(0, 3, Tetris.L);
		cells[2] = new Cell(0, 5, Tetris.L);
		cells[3] = new Cell(1, 3, Tetris.L);
		states = new State[4];
		states[0] = new State(0,0,0,1,0,-1,-1,1);
		states[1] = new State(0,0,1,0,-1,0,1,1);
		states[2] = new State(0,0,0,-1,0,1,1,-1);
		states[3] = new State(0,0,-1,0,1,0,-1,-1);
	}
}
class J extends Tetromino{
	public J() {
		cells[0] = new Cell(0, 4, Tetris.J);
		cells[1] = new Cell(0, 3, Tetris.J);
		cells[2] = new Cell(0, 5, Tetris.J);
		cells[3] = new Cell(1, 5, Tetris.J);
		states = new State[4];
		states[0] = new State(0,0,0,-1,0,1,1,1);
		states[1] = new State(0,0,-1,0,1,0,1,-1);
		states[2] = new State(0,0,0,1,0,-1,-1,-1);
		states[3] = new State(0,0,1,0,-1,0,-1,1);
	}
}