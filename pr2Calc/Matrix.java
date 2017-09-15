package pr2Calc;

public class Matrix {
        protected double[][] m_;
        
        public Matrix(double[][] input){
        // �񎟌��z�� input �̓��e�ŁA�t�B�[���h�ϐ� m_[][]�̓��e������������
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
                // �w�肵���͈͂����݂��Ȃ��ꍇ
                if(row<0 && row>m_.length && column<0 && column>m_[0].length){
                        System.out.println("�w�肷��v�f�͑��݂��܂���.");
                        System.exit(0);
                }
                return m_[row][column];
        }
        
        // �s��̓��e��\�����郁�\�b�h�i_print�ȍ~�ɓK���Ȗ��O������j
        // �K�v�ɉ����āA�����ň����̈قȂ郁�\�b�h�𕡐��쐬���Ă��ǂ�
        protected void _printMatrix(){
        // �s����e�̕\����������������
        	int i,j;
        	for(i=0;i<m_.length;i++){
        		for(j=0;j<m_[0].length;j++){
        			System.out.print(String.format("%1$7.2f ", m_[i][j]));
        		}
        		System.out.print("\n");
        	}
        }
        
        // �s�񓯎m�A�������͍s��Ɨ�x�N�g���Ƃ̐ς��v�Z����
        protected double[][] _multiplyMatrix(double[][] target){
                double[][] retValue = new double[m_.length][target[0].length];
				int i,j,k;
                // �|������s��̗񐔂Ɗ|����s��̍s�����������Ȃ�
                if(m_[0].length == target.length){
                        // �ς̌v�Z��������������
                	for(i=0;i<m_.length;i++){
                		for(j=0;j<target[0].length;j++){
                			retValue[i][j]=0;
                			for(k=0;k<target.length;k++){
                				retValue[i][j]+=m_[i][k]*target[k][j];
                			}
                		}
                	}
		//�s�x�N�g���ƍs�x�N�g���̓���
                }else if(m_.length ==  target.length && m_[0].length == 1 && target[0].length ==1){
				retValue = new double[m_[0].length][target[0].length];
				retValue[0][0]=0;
                	for(k=0;k<target.length;k++){
                		retValue[0][0]+=m_[k][0]*target[k][0];
                	}
                }
		else{
                        System.out.println("�v�f�����v�Z�ł���g�ݍ��킹�ƂȂ��Ă��܂���");
                        System.exit(0);
                }
                return retValue;
        }
        
        /* ��̃��\�b�h�̑���Ɂi�������́A��̃��\�b�h�ɉ����āj�߂�l�ƈ�����
         * �o���� Matrix �N���X�Ƃ��� _multiplyMatrix()���쐬���Ă��ǂ�
         * �ǂ��炪�g���₷�����͊e���Ŕ��f���ė~����
         */

		
        
        public static void main(String[] args) {
/*
 * main ���\�b�h���ō���쐬�������όv�Z���\�b�h��s�񓯎m�A�x�N�g���ƍs��A
 * �s��ƃx�N�g���̐ς��v�Z���郁�\�b�h������ɓ����Ă��邩���m�F����B
 */

// �s��E�x�N�g����`�E�����̈�� �i�����܂ň��@�ۑ�̗v���𖞂����悤�A
// �ȍ~�̕����͎��R�ɋL�q���C�����č\��Ȃ��j
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
                
// ���̃��\�b�h _printMatrix() �́A�s��I�u�W�F�N�g�̓��e��\�����邽�߂̎����̈��
//�i�s�v�ł���΍폜���ĉ������j]
				System.out.println("�s��̓]�u�`�]�u�O�s��`");
				mat0 = new Matrix(E);
				mat0._printMatrix();
				I = mat0._transpose();
				mat1 = new Matrix(I);
				System.out.println("\n�s��̓]�u�`�]�u��s��`");
                mat1._printMatrix();
                
                System.out.println("\n���W�_�̉�]1.�`45����]�O���W�`");
                mat0 = new Matrix(A);
                mat0._printMatrix();
                System.out.println("\n���W�_�̉�]1.�`45����]����W�`");
				J = mat0._rotate(45);
				mat1 = new Matrix(J);
                mat1._printMatrix();
                
                System.out.println("\n���W�_�̉�]2.�`60����]�O���W�`");
                mat0 = new Matrix(B);
                mat0._printMatrix();
                System.out.println("\n���W�_�̉�]2.�`60����]����W�`");
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
        		System.out.println("�����2�������W�_�ł͂���܂���");
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
