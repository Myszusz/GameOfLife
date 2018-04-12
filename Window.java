import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Window {

	private JFrame frame;
	private JTable table;
	private JTextField txtAlive;
	private JTextField txtBorn;
	boolean isGo = false;
	Game game = new Game();
	public Timer timer;
	private JTextField rowstxt;
	private JTextField columnstxt;
	private JTextField percenttxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		game.iniBoard();
		timer = new Timer(200, this::timerStep);
		timer.stop();
	}
	
	private void timerStep(ActionEvent e)
	  {
		game.goNextStep();
		updateUI();
	  }

	public void updateUI() {
		DefaultTableModel dtm = game.getModel();
		table.setModel(dtm);
		dtm.fireTableDataChanged();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				updateUI();
				
			}
		});
		frame.setBounds(100, 100, 1013, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGo = new JButton("Play");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(!isGo){
					timer.start();
					btnGo.setText("Pause");
					isGo = true;
				}
				else{
					timer.stop();
					btnGo.setText("Play");
					isGo = false;
				}
				
			}
		});
		
		JLabel lblAlive = new JLabel("Alive");
		
		JLabel lblBorn = new JLabel("Born");
		
		JButton btnClearBoard = new JButton("Clear Board");
		btnClearBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				game.iniBoard();
				updateUI();
			}
			
		});
		
		JButton btnDeafult = new JButton("Deafult");
		btnDeafult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtAlive.setText("23");
				txtBorn.setText("3");
				game.setRules(game.getFieldValue("23"), game.getFieldValue("3"));
			}
		});
		
		JLabel lblRows = new JLabel("Rows");
		
		JLabel lblColumns = new JLabel("Columns");
		
		txtAlive = new JTextField();
		txtAlive.setText("23");
		txtAlive.setColumns(10);
		
		txtBorn = new JTextField();
		txtBorn.setText("3");
		txtBorn.setColumns(10);
		
		JButton btnSetRules = new JButton("Set Rules");
		btnSetRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strAlive = txtAlive.getText();
				String strBorn = txtBorn.getText();
				game.setRules(game.getFieldValue(strAlive), game.getFieldValue(strBorn));
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		panel.add(table);
		
		rowstxt = new JTextField();
		rowstxt.setText("30");
		rowstxt.setColumns(10);
		
		columnstxt = new JTextField();
		columnstxt.setText("60");
		columnstxt.setColumns(10);
		
		JButton btnSetSize = new JButton("Set Size");
		btnSetSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.stop();
				game.numberOfRows = Integer.parseInt(rowstxt.getText());
				game.numberOfColumns = Integer.parseInt(columnstxt.getText());
				
				game.iniBoard();
				updateUI();
				
			}
		});
		
		JButton btnRandomize = new JButton("Randomize");
		btnRandomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				game.randomize(Double.parseDouble(percenttxt.getText()));
				updateUI();
				
			}
		});
		
		percenttxt = new JTextField();
		percenttxt.setText("0.00");
		percenttxt.setColumns(10);
		
		JLabel lblPercentOfFilling = new JLabel("percent of filling (0-1)");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(btnGo, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAlive, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAlive, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBorn, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtBorn, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDeafult, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSetRules, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addComponent(btnClearBoard, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblRows, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(lblColumns, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rowstxt, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(columnstxt, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(btnSetSize, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRandomize, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(percenttxt, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblPercentOfFilling, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
					.addGap(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGo, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(lblAlive)
							.addGap(13)
							.addComponent(txtAlive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(lblBorn)
							.addGap(13)
							.addComponent(txtBorn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDeafult, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnSetRules, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnClearBoard, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRows)
								.addComponent(lblColumns))
							.addGap(13)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rowstxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(columnstxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addComponent(btnSetSize, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(btnRandomize, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addComponent(percenttxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblPercentOfFilling, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
					.addGap(13))
		);
		frame.getContentPane().setLayout(groupLayout);
		table.setDefaultEditor(Object.class, null);
		//table.setEnabled(false);
		
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                if(e.getClickCount() == 1){
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    System.out.println("MOUSE "+ row + "," + col );
                    if (row < 0 || col < 0) {
                        return;
                    }
                    game.ChangeSelectedCell(row, col);
                    ((DefaultTableModel)table.getModel()).fireTableCellUpdated(row, col);
                }
            }
        });
	}
}
