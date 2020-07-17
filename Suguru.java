//201714015
//Akib Uz Zaman
//Book_4
//Problem_16

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

public class Suguru {

    public void modelAndSolve(){
        
        Model model = new Model("Suguru problem");
        IntVar[][] board = model.intVarMatrix("board", 6, 6, 1, 5);
        
        IntVar[] b0 = model.intVarArray("b0", 5, 1, 5);
        IntVar[] b1 = model.intVarArray("b1", 5, 1, 5);
        IntVar[] b2 = model.intVarArray("b2", 5, 1, 5);
        IntVar[] b3 = model.intVarArray("b3", 5, 1, 5);
        IntVar[] b4 = model.intVarArray("b4", 5, 1, 5);
        IntVar[] b5 = model.intVarArray("b5", 5, 1, 5);
        IntVar[] b6 = model.intVarArray("b6", 4, 1, 4);
        IntVar[] b7 = model.intVarArray("b7", 2, 1, 2);
        
        model.arithm(board[0][0], "=", 3).post();
        model.arithm(board[0][2], "=", 4).post();
        model.arithm(board[1][5], "=", 2).post();
        model.arithm(board[2][3], "=", 4).post();
        model.arithm(board[2][4], "=", 3).post();
        model.arithm(board[4][0], "=", 2).post();
        model.arithm(board[5][4], "=", 4).post();
        
        model.arithm(board[0][1], "=", b0[0]).post();
        model.arithm(board[0][2], "=", b0[1]).post();
        model.arithm(board[0][3], "=", b0[2]).post();
        model.arithm(board[0][4], "=", b0[3]).post();
        model.arithm(board[1][0], "=", b0[4]).post();
        
        model.arithm(board[0][4], "=", b1[0]).post();
        model.arithm(board[0][5], "=", b1[1]).post();
        model.arithm(board[1][4], "=", b1[2]).post();
        model.arithm(board[1][5], "=", b1[3]).post();
        model.arithm(board[2][5], "=", b1[4]).post();
        
        model.arithm(board[1][1], "=", b2[0]).post();
        model.arithm(board[1][2], "=", b2[1]).post();
        model.arithm(board[2][0], "=", b2[2]).post();
        model.arithm(board[2][1], "=", b2[3]).post();
        model.arithm(board[3][1], "=", b2[4]).post();
        
        model.arithm(board[1][3], "=", b3[0]).post();
        model.arithm(board[2][2], "=", b3[1]).post();
        model.arithm(board[2][3], "=", b3[2]).post();
        model.arithm(board[2][4], "=", b3[3]).post();
        model.arithm(board[3][2], "=", b3[4]).post();
        
        model.arithm(board[3][0], "=", b4[0]).post();
        model.arithm(board[4][0], "=", b4[1]).post();
        model.arithm(board[4][1], "=", b4[2]).post();
        model.arithm(board[5][0], "=", b4[3]).post();
        model.arithm(board[5][1], "=", b4[4]).post();
        
        model.arithm(board[3][5], "=", b5[0]).post();
        model.arithm(board[4][4], "=", b5[1]).post();
        model.arithm(board[4][5], "=", b5[2]).post();
        model.arithm(board[5][4], "=", b5[3]).post();
        model.arithm(board[5][5], "=", b5[4]).post();
        
        model.arithm(board[3][3], "=", b6[0]).post();
        model.arithm(board[3][4], "=", b6[1]).post();
        model.arithm(board[4][2], "=", b6[2]).post();
        model.arithm(board[4][3], "=", b6[3]).post();
        
        model.arithm(board[5][2], "=", b7[0]).post();
        model.arithm(board[5][3], "=", b7[1]).post();
        
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if(j<5)
                    model.arithm(board[i][j], "!=", board[i][j+1]).post(); //cell_of_next_row
                if(j>0)
                    model.arithm(board[i][j], "!=", board[i][j-1]).post(); //cell_of_prev_row
                if(i<5)
                    model.arithm(board[i][j], "!=", board[i+1][j]).post(); //cell_of_down_col
                if(i>0)
                    model.arithm(board[i][j], "!=", board[i-1][j]).post(); //cell_of_upper_col
                if(i>0 && j>0)
                    model.arithm(board[i][j], "!=", board[i-1][j-1]).post(); //diagonal
                if(i>0 && j<5)
                    model.arithm(board[i][j], "!=", board[i-1][j+1]).post(); //diagonal
                if(i<5 && j>0)
                    model.arithm(board[i][j], "!=", board[i+1][j-1]).post(); //diagonal
                if(i<5 && j<5)
                    model.arithm(board[i][j], "!=", board[i+1][j+1]).post(); //diagonal
            }
        }
        
        model.allDifferent(b0).post();
        model.allDifferent(b1).post();
        model.allDifferent(b2).post();
        model.allDifferent(b3).post();
        model.allDifferent(b4).post();
        model.allDifferent(b5).post();
        model.allDifferent(b6).post();
        model.allDifferent(b7).post();
        
        Solver solver = model.getSolver();
        solver.showStatistics();
        solver.showSolutions();
        solver.findSolution();
        
        //print board
        System.out.println("-------------------------");
		for (int i = 0; i < 6; i++) {
			System.out.print("| ");
			for (int j = 0; j < 6; j++) {
			    int k = board[i][j].getValue();
				System.out.print(k+" | ");
			}
			System.out.println();
			System.out.println("-------------------------");
		}
    }

    public static void main(String[] args) {
        new Suguru().modelAndSolve();
    }

}