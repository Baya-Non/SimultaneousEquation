package pr2Calc;

public class Matrix {
        protected double[][] m_;
        
        public Matrix(double[][] input){
        // 二次元配列 input の内容で、フィールド変数 m_[][]の内容を初期化せよ
        	int i,j;
        	m_= new double [input.length][input[0].length];
        	for(i=0;i<input.length;i++){
        		for(j=0;j<input[0].length;j++){
        			m_[i][j]=input[i][j];
        		}
        	}
        }

        protected int _getNumOfRow(){
                return m_.length;
        }
        
        protected int _getNumOfColumn(){
                return m_[0].length;
        }
        
        protected double _getComponentOf(int row, int column){
                // 指定した範囲が存在しない場合
                if(row<0 && row>m_.length && column<0 && column>m_[0].length){
                        System.out.println("指定する要素は存在しません.");
                        System.exit(0);
                }
                return m_[row][column];
        }
        
        // 行列の内容を表示するメソッド（_print以降に適当な名前をつけよ）
        // 必要に応じて、同名で引数の異なるメソッドを複数作成しても良い
        protected void _printMatrix(){
        // 行列内容の表示処理を実装せよ
        	int i,j;
        	for(i=0;i<m_.length;i++){
        		for(j=0;j<m_[0].length;j++){
        			System.out.print(String.format("%1$7.2f ", m_[i][j]));
        		}
        		System.out.print("\n");
        	}
        }
        
        // 行列同士、もしくは行列と列ベクトルとの積を計算する
        protected double[][] _multiplyMatrix(double[][] target){
                double[][] retValue = new double[m_.length][target[0].length];
				int i,j,k;
                // 掛けられる行列の列数と掛ける行列の行数が等しいなら
                if(m_[0].length == target.length){
                        // 積の計算処理を実装せよ
                	for(i=0;i<m_.length;i++){
                		for(j=0;j<target[0].length;j++){
                			retValue[i][j]=0;
                			for(k=0;k<target.length;k++){
                				retValue[i][j]+=m_[i][k]*target[k][j];
                			}
                		}
                	}
		//行ベクトルと行ベクトルの内積
                }else if(m_.length ==  target.length && m_[0].length == 1 && target[0].length ==1){
				retValue = new double[m_[0].length][target[0].length];
				retValue[0][0]=0;
                	for(k=0;k<target.length;k++){
                		retValue[0][0]+=m_[k][0]*target[k][0];
                	}
                }
		else{
                        System.out.println("要素数が計算できる組み合わせとなっていません");
                        System.exit(0);
                }
                return retValue;
        }
        
        /* 上のメソッドの代わりに（もしくは、上のメソッドに加えて）戻り値と引数の
         * 双方を Matrix クラスとした _multiplyMatrix()を作成しても良い
         * どちらが使いやすいかは各自で判断して欲しい
         */

		
        
        public static void main(String[] args) {
/*
 * main メソッド中で今回作成した内積計算メソッドや行列同士、ベクトルと行列、
 * 行列とベクトルの積を計算するメソッドが正常に動いているかを確認せよ。
 */

// 行列・ベクトル定義・処理の一例 （あくまで一例　課題の要求を満たすよう、
// 以降の部分は自由に記述を修正して構わない）
                Matrix mat0,mat1;
                double[][] A = {
                                    { -3.0},
                                    { 3.0},},
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
				System.out.println("行列の転置～転置前行列～");
				mat0 = new Matrix(E);
				mat0._printMatrix();
				I = mat0._transpose();
				mat1 = new Matrix(I);
				System.out.println("\n行列の転置～転置後行列～");
                mat1._printMatrix();
                
                System.out.println("\n座標点の回転1.～45°回転前座標～");
                mat0 = new Matrix(A);
                mat0._printMatrix();
                System.out.println("\n座標点の回転1.～45°回転後座標～");
				J = mat0._rotate(45);
				mat1 = new Matrix(J);
                mat1._printMatrix();
                
                System.out.println("\n座標点の回転2.～60°回転前座標～");
                mat0 = new Matrix(B);
                mat0._printMatrix();
                System.out.println("\n座標点の回転2.～60°回転後座標～");
				J = mat0._rotate(60);
				mat1 = new Matrix(J);
                mat1._printMatrix();
                

        }
        
        protected double[][] _transpose(){
			double[][] trace = new double[_getNumOfColumn()][_getNumOfRow()];
			int i,j;
			for(i=0;i<_getNumOfRow();i++){
				for(j=0;j<_getNumOfColumn();j++){
					trace[j][i] = m_[i][j];
				}
			}
			return trace;
		}
		
		protected double _toRadianValue(double theta){
			theta = theta * Math.PI / 180;
			return theta;
		}
        
        protected double[][] _rotate(double theta){
        	if(_getNumOfRow() == 1 && _getNumOfColumn() == 2){
        		m_ = _transpose();
        	}else if(_getNumOfRow() != 2 && _getNumOfColumn() != 1){
        		System.out.println("これは2次元座標点ではありません");
                System.exit(0);
        	}
        	double[][] move = m_;
        	m_ = new double[2][2];
        	theta = _toRadianValue(theta);
        	m_[0][0] = Math.cos(theta);
        	m_[0][1] = -Math.sin(theta);
        	m_[1][0] = Math.sin(theta);
        	m_[1][1] = Math.cos(theta);
        	m_ = _multiplyMatrix(move);
        	
        	return m_;
        }

}
