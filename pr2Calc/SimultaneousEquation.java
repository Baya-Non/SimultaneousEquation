package pr2Calc;

public class SimultaneousEquation extends Matrix{
	protected double[] answers_;

	public SimultaneousEquation(double[][] input){
		super(input);
		answers_ = new double[_getNumOfRow()];
	}
	protected void _normalize(int select_row){
		int i,j;
		for(i=select_row+1;i<_getNumOfColumn();i++){
			m_[select_row][i] = m_[select_row][i] / m_[select_row][select_row];
		}
		m_[select_row][select_row] = m_[select_row][select_row] / m_[select_row][select_row];
		
		for(j=0;j<_getNumOfRow();j++){
			if(j!=select_row){
				_subtractRowFrom(select_row,j);
			}
		}


	}

	protected void _subtractRowFrom(int sub_row, int min_row){
		int i,j;
		double[][] sa = new double [_getNumOfRow()][_getNumOfColumn()];
		for(i=0;i<_getNumOfRow();i++){
			for(j=0;j<_getNumOfColumn();j++){
				sa[i][j]=m_[i][j];
			}
		}
		for(i=0;i<_getNumOfColumn();i++){
			sa[sub_row][i] *=sa[min_row][sub_row];
		}
		for(i=0;i<_getNumOfColumn();i++){
			m_[min_row][i] -=sa[sub_row][i];
		}
	}
	
	
		
	public static void main(String[] args) {
/*
 * main メソッド中で今回作成した内積計算メソッドや行列同士、ベクトルと行列、
 * 行列とベクトルの積を計算するメソッドが正常に動いているかを確認せよ。
 */

// 行列・ベクトル定義・処理の一例 （あくまで一例　課題の要求を満たすよう、
// 以降の部分は自由に記述を修正して構わない）
                SimultaneousEquation mat0,mat1;
                int i,j;
                double[][] A = {
                                    { 2.0, 1.0, 3.0, 4.0, 2.0},
                                    { 3.0, 2.0, 5.0, 2.0, 12.0},
                                    { 3.0, 4.0, 1.0,-1.0, 4.0},
                                    {-1.0,-3.0, 1.0, 3.0,-1.0},},
                           B = {
                                    { 2.0},
                                    {-3.464}},
                           E = {
                                    { 1.0, 2.0, 3.0},
                                    { 3.0, 2.0,-1.0},
                                    { 4.0, 2.0, 6.0}},
                           I,
                           J;
                
// 下のメソッド _printMatrix() は、行列オブジェクトの内容を表示するための実装の一例
//（不要であれば削除して下さい）]
			System.out.println("～ヤコビ前行列～");
			mat0 = new SimultaneousEquation(A);
			mat0._printMatrix();
			for(i=0;i<mat0._getNumOfRow();i++){
				mat0._normalize(i);
			}
			for(i=0;i<mat0._getNumOfRow();i++){
				mat0.answers_[i]=mat0.m_[i][mat0._getNumOfColumn()-1];
				System.out.println(mat0.answers_[i]);
			}
			
			
			System.out.println("～ヤコビ後行列～");
			mat0._printMatrix();
            
            

    }
}

