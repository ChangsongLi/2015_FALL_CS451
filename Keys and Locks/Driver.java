import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.*;

public class Driver implements ActionListener {
	JFrame window, window2,window3;
	JPanel searchField, addDelSear;
	JScrollPane scroller;
	JButton key, lock, keyLock, add, search, delete, win2Add, win2AddKey, win2Asign,win2Design,yes;
	Color darkBlue = new Color(119, 136, 153);
	Color white = new Color(255, 255, 240);
	Color purple = new Color(147, 112, 219);
	JComboBox<String> addSel, searchSel;
	Main data;
	JLabel display;
	int kID = 0, lID = 0;
	JComboBox<String> addBuilding, addRoom, addKey, keyType, swipeCard,lockForKeys, lockForSC, keyAllLock, lockAllKey, buildingLock,
			allKey;

	public static void main(String[] args) {
		Driver d = new Driver();
	}

	Driver() {
		creatWindow();
		data = new Main();
	}

	public void creatWindow() {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(100, 0, 1000, 600);
		window.setResizable(true);
		window.setLayout(null);
		window.getContentPane().setBackground(Color.gray);
		window.setTitle("Key and Lock");

		searchField = new JPanel();
		searchField.setBounds(5, 15, 650, 70);
		searchField.setBackground(darkBlue);
		searchField.setBorder(BorderFactory.createLineBorder(darkBlue, 3));
		searchField.setLayout(new FlowLayout(1, 50, 20));

		key = new JButton("Key View");
		key.setSize(80, 30);
		key.setBackground(white);
		key.setOpaque(true);
		key.setBorderPainted(false);
		key.setForeground(Color.black);
		key.addActionListener(this);
		searchField.add(key);

		lock = new JButton("Lock View");
		lock.setSize(80, 30);
		lock.setBackground(white);
		lock.setOpaque(true);
		lock.setBorderPainted(false);
		lock.setForeground(Color.black);
		lock.addActionListener(this);
		searchField.add(lock);

		keyLock = new JButton("Key-Lock View");
		keyLock.setSize(80, 30);
		keyLock.addActionListener(this);
		keyLock.setBackground(white);
		keyLock.setOpaque(true);
		keyLock.setBorderPainted(false);
		keyLock.setForeground(Color.black);
		searchField.add(keyLock);
		window.add(searchField);

		scroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setBounds(5, 100, 650, 450);
		scroller.setLayout(null);
		scroller.setBackground(darkBlue);
		scroller.setBorder(BorderFactory.createLineBorder(darkBlue, 3));
		window.add(scroller);

		addDelSear = new JPanel();
		addDelSear.setLayout(null);
		addDelSear.setBounds(700, 15, 250, 540);
		addDelSear.setBackground(darkBlue);
		window.add(addDelSear);

		String[] addStr = { "a new lock to the system", "a new key to the system", "an exist key to an exist lock" };
		addSel = new JComboBox<String>(addStr);
		addSel.setBounds(5, 60, 240, 30);
		addDelSear.add(addSel);

		add = new JButton("Add");
		add.setBounds(80, 100, 80, 30);
		add.setBackground(white);
		add.setOpaque(true);
		add.addActionListener(this);
		add.setBorderPainted(false);
		add.setForeground(Color.black);
		addDelSear.add(add);

		String[] searchType = { "all locks for a given key ID", "all keys for a given lock ID",
				"all locks in a given building" };
		searchSel = new JComboBox<String>(searchType);
		searchSel.setBounds(5, 220, 240, 30);
		addDelSear.add(searchSel);

		search = new JButton("Search");
		search.setBounds(75, 260, 90, 30);
		search.setBackground(white);
		search.setOpaque(true);
		search.addActionListener(this);
		search.setBorderPainted(false);
		search.setForeground(Color.black);
		addDelSear.add(search);

		JLabel l = new JLabel(" Delete exist key to exist lock.");
		l.setBounds(20, 380, 200, 30);
		l.setBackground(darkBlue);
		l.setForeground(Color.white);
		l.setOpaque(true);
		addDelSear.add(l);

		delete = new JButton("Delete");
		delete.setBounds(75, 420, 90, 30);
		delete.setBackground(white);
		delete.setOpaque(true);
		delete.addActionListener(this);
		delete.setBorderPainted(false);
		delete.setForeground(Color.black);
		addDelSear.add(delete);

		window.setVisible(true);
	}

	public void updateDisplayPanel() {
		window.remove(scroller);
		scroller = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setBounds(5, 100, 650, 450);
		scroller.setLayout(null);
		scroller.setBackground(darkBlue);
		scroller.setBorder(BorderFactory.createLineBorder(darkBlue, 3));
		window.add(scroller);
	}

	public void secondWindow() {
		if (window2 != null) {
			window2.setVisible(false);
		}
		window2 = new JFrame();
		window2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window2.setBounds(400, 200, 400, 400);
		window2.setResizable(true);
		window2.setLayout(null);
		window2.getContentPane().setBackground(Color.gray);
	}
	
	public void thirdWindow() {
		if (window3 != null) {
			window3.setVisible(false);
		}
		window3 = new JFrame();
		window3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window3.setBounds(300, 300, 600, 100);
		window3.setResizable(true);
		window3.setLayout(null);
		window3.getContentPane().setBackground(Color.gray);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == key) {
			updateDisplayPanel();
			int count = 0;
			String type;
			int w, h;

			key.setBackground(Color.black);
			key.setForeground(white);

			keyLock.setForeground(Color.black);
			keyLock.setBackground(white);
			lock.setForeground(Color.black);
			lock.setBackground(white);

			HashSet<Key1> k = data.getAllKeys();
			Key1 temp = null;
			Iterator<Key1> iter = k.iterator();
			while (iter.hasNext()) {
				temp = iter.next();
				if (temp.getType() == true) {
					type = "key.png";
					w = 45;
					h = 40;
				} else {
					type = "swipecard.png";
					w = 60;
					h = 40;
				}

				ImageIcon ic = new ImageIcon(getClass().getResource(type));
				JLabel j = new JLabel(ic);
				j.setBounds(20 + 90 * (count % 7), 10 + 90 * (count / 7), w, h);
				j.setOpaque(true);
				scroller.add(j);

				JLabel j2 = new JLabel("ID: " + temp.getID());
				j2.setBounds(20 + 90 * (count % 7), 60 + 90 * (count / 7), 60, 10);
				j2.setOpaque(true);
				scroller.add(j2);

				count++;
			}
		} else if (e.getSource() == lock) {
			updateDisplayPanel();
			int count = 0;

			lock.setBackground(Color.black);
			lock.setForeground(white);

			keyLock.setForeground(Color.black);
			keyLock.setBackground(white);
			key.setForeground(Color.black);
			key.setBackground(white);

			HashSet<Lock1> l = data.getAllLocks();
			Lock1 temp = null;
			Iterator<Lock1> iter = l.iterator();

			while (iter.hasNext()) {
				temp = iter.next();

				ImageIcon ic = new ImageIcon(getClass().getResource("lock.png"));
				JLabel j = new JLabel(ic);
				j.setBounds(35 + 90 * (count % 7), 10 + 90 * (count / 7), 35, 50);
				j.setOpaque(true);
				scroller.add(j);

				JLabel j2 = new JLabel("ID: " + temp.getID());
				j2.setBounds(25 + 90 * (count % 7), 65 + 90 * (count / 7), 60, 10);
				j2.setOpaque(true);
				scroller.add(j2);

				JLabel j3 = new JLabel(temp.getBuilding().substring(0, 4) + ": " + temp.getRoomNumber());
				j3.setBounds(20 + 90 * (count % 7), 78 + 90 * (count / 7), 70, 10);
				j3.setOpaque(true);
				scroller.add(j3);

				count++;
			}
		} else if (e.getSource() == keyLock) {
			updateDisplayPanel();
			int count = 0;
			String type;

			keyLock.setBackground(Color.black);
			keyLock.setForeground(white);

			key.setForeground(Color.black);
			key.setBackground(white);
			lock.setForeground(Color.black);
			lock.setBackground(white);

			HashSet<KeyLock> kL = data.getAllCombos();
			KeyLock temp = null;
			Iterator<KeyLock> iter = kL.iterator();
			while (iter.hasNext()) {
				temp = iter.next();
				Key1 k = data.searchKey(temp.getKeyID());
				if (k.getType() == true) {
					type = "keylock.png";
				} else {
					type = "swipecardlock.png";
				}

				ImageIcon ic = new ImageIcon(getClass().getResource(type));
				JLabel j = new JLabel(ic);
				j.setBounds(20 + 90 * (count % 7), 10 + 110 * (count / 7), 50, 50);
				j.setOpaque(true);
				scroller.add(j);

				JLabel j2 = new JLabel("  Key: " + temp.getKeyID());
				j2.setBounds(10 + 90 * (count % 7), 70 + 110 * (count / 7), 70, 10);
				j2.setOpaque(true);
				scroller.add(j2);

				JLabel j3 = new JLabel("Lock: " + temp.getLockID());
				j3.setBounds(10 + 90 * (count % 7), 85 + 110 * (count / 7), 70, 10);
				j3.setOpaque(true);
				scroller.add(j3);

				count++;
			}
		} else if (e.getSource() == add) {
			String choose = (String) addSel.getSelectedItem();
			if (choose.equals("a new lock to the system")) {
				secondWindow();
				window2.setTitle("Add a new lock to the system");
				String[] b = { "Select Building", "WING TECH CENTER", "CENTENNIAL" };
				addBuilding = new JComboBox<String>(b);
				addBuilding.addActionListener(this);
				addBuilding.setBounds(80, 50, 250, 30);
				window2.add(addBuilding);

				window2.setVisible(true);
			} else if (choose.equals("a new key to the system")) {
				secondWindow();
				window2.setTitle("Add a new lock to the system");
				int size = data.getAllLocks().size() + 1;
				String[] lockNum = new String[size];
				lockNum[0] = "Select a lock to add a new key";
				for (int i = 1; i < size; i++) {
					lockNum[i] = "" + (i + 1000);
				}
				addKey = new JComboBox<String>(lockNum);
				addKey.setBounds(80, 50, 250, 30);
				window2.add(addKey);

				String[] t = { "Key", "Swipe Card" };
				keyType = new JComboBox<String>(t);
				keyType.setBounds(80, 150, 250, 30);
				window2.add(keyType);

				win2AddKey = new JButton("Add");
				win2AddKey.addActionListener(this);
				win2AddKey.setBounds(80, 250, 250, 30);
				win2AddKey.setForeground(white);
				win2AddKey.setBackground(darkBlue);
				win2AddKey.setOpaque(true);
				win2AddKey.setBorderPainted(false);
				window2.add(win2AddKey);

				window2.setVisible(true);
			} else {
				secondWindow();
				window2.setTitle("Add an existing key to an existing lock.");

				swipeCard = new JComboBox<String>(getSwipeCards());
				swipeCard.setBounds(80, 50, 250, 30);
				swipeCard.addActionListener(this);
				window2.add(swipeCard);

				window2.repaint();
				window2.setVisible(true);
			}
		} else if (e.getSource() == search) {
			String choose = (String) searchSel.getSelectedItem();
			if (choose.equals("all locks for a given key ID")) {
				secondWindow();
				window2.setTitle("All locks for a given key ID");
				String[] s = new String[data.getAllKeys().size() + 1];
				s[0] = "Select a key,finding all locks for it";
				for (int i = 1; i < data.getAllKeys().size() + 1; i++) {
					s[i] = "" + i;
				}
				window2.setSize(600, 200);
				keyAllLock = new JComboBox<String>(s);
				keyAllLock.addActionListener(this);
				keyAllLock.setBounds(40, 50, 300, 30);
				window2.add(keyAllLock);

				window2.repaint();
				window2.setVisible(true);
			} else if (choose.equals("all keys for a given lock ID")) {
				secondWindow();
				window2.setTitle("All keys for a given lock ID");
				String[] s = new String[data.getAllKeys().size() + 1];
				s[0] = "Select a lock,finding all keys for it";
				for (int i = 1; i < data.getAllLocks().size() + 1; i++) {
					s[i] = "" + (i + 1000);
				}
				window2.setSize(600, 200);
				lockAllKey = new JComboBox<String>(s);
				lockAllKey.addActionListener(this);
				lockAllKey.setBounds(40, 50, 300, 30);
				window2.add(lockAllKey);

				window2.repaint();
				window2.setVisible(true);
			} else {
				secondWindow();
				window2.setSize(600, 200);
				window2.setTitle("All locks in a given building");
				String[] s = new String[3];
				s[0] = " All locks in a given building";
				s[1] = "WING TECH CENTER";
				s[2] = "CENTENNTIAL";
				buildingLock = new JComboBox<String>(s);
				buildingLock.addActionListener(this);
				buildingLock.setBounds(40, 50, 300, 30);
				window2.add(buildingLock);

				window2.repaint();
				window2.setVisible(true);
			}
		} else if (e.getSource() == delete) {
			secondWindow();
			window2.setTitle("Delete an existing key to an existing lock.");

			allKey = new JComboBox<String>(getSwipeCards());
			allKey.setBounds(80, 50, 250, 30);
			allKey.addActionListener(this);
			window2.add(allKey);

			window2.repaint();
			window2.setVisible(true);

		} else if (e.getSource() == buildingLock) {
			if (display != null) {
				window2.remove(display);
			}
			if (buildingLock.getSelectedIndex() == 1) {
				display = new JLabel();
				display.setBounds(20, 100, 500, 30);
				display.setBackground(Color.white);
				String s = "Locks for that building: ";
				HashSet<Lock1> lock = null;
				try {
					lock = data.searchAllLocksInBuilding("WING TECH CENTER");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				Lock1 temp = null;
				Iterator<Lock1> iter = lock.iterator();
				while (iter.hasNext()) {
					temp = iter.next();
					s = s + " " + temp.getID();
				}
				display.setText(s);
				window2.add(display);
			} else if (buildingLock.getSelectedIndex() == 2) {
				display = new JLabel();
				display.setBounds(20, 100, 500, 30);
				display.setBackground(Color.white);
				String s = "Locks for that building: ";
				HashSet<Lock1> lock = null;
				try {
					lock = data.searchAllLocksInBuilding("CENTENNIAL");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				Lock1 temp = null;
				Iterator<Lock1> iter = lock.iterator();
				while (iter.hasNext()) {
					temp = iter.next();
					s = s + " " + temp.getID();
				}
				display.setText(s);
				window2.add(display);
			}

			window2.repaint();
			window2.setVisible(true);

		} else if (e.getSource() == lockAllKey) {
			if (display != null) {
				window2.remove(display);
			}
			if (lockAllKey.getSelectedIndex() != 0) {
				display = new JLabel();
				display.setBounds(20, 100, 500, 30);
				display.setBackground(Color.white);
				String s = "Keys for that lock: ";
				int num = lockAllKey.getSelectedIndex();
				HashSet<Key1> lock = null;
				try {
					lock = data.searchKeysOpeningGivenLock(num + 1000);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				Key1 temp = null;
				Iterator<Key1> iter = lock.iterator();
				while (iter.hasNext()) {
					temp = iter.next();
					s = s + " " + temp.getID();
				}
				display.setText(s);
				window2.add(display);
				window2.repaint();
				window2.setVisible(true);
			} else {
				if (display != null) {
					window2.remove(display);
				}
				window2.repaint();
				window2.setVisible(true);
			}

		} else if (e.getSource() == keyAllLock) {
			if (display != null) {
				window2.remove(display);
			}
			if (keyAllLock.getSelectedIndex() != 0) {
				display = new JLabel();
				display.setBounds(20, 100, 500, 30);
				display.setBackground(Color.white);
				String s = "Locks for that key: ";
				int num = keyAllLock.getSelectedIndex();
				HashSet<Lock1> lock = null;
				try {
					lock = data.searchLocksOpenedByGivenKey(num);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				Lock1 temp = null;
				Iterator<Lock1> iter = lock.iterator();
				while (iter.hasNext()) {
					temp = iter.next();
					s = s + " " + temp.getID();
				}
				display.setText(s);
				window2.add(display);
				window2.repaint();
				window2.setVisible(true);
			} else {
				if (display != null) {
					window2.remove(display);
				}
				window2.repaint();
				window2.setVisible(true);
			}

		} 
		else if (e.getSource() == win2Design) {
			String kI = (String) allKey.getSelectedItem();
			String lI = (String) lockForKeys.getSelectedItem();
			if(lI== null){
				thirdWindow();
				JLabel j = new JLabel("No lock for this key." );
				j.setForeground(Color.red);
				j.setBounds(40, 20, 500, 30);
				window3.add(j);
				window3.repaint();
				window3.setVisible(true);
			}
			else{
				int keyID = Integer.parseInt(kI);
				Scanner scan = new Scanner(lI);
				scan.next();
				int lockID = scan.nextInt();
				try {
					thirdWindow();
					JLabel j = new JLabel("Are you sure for deleting?  "+"the keyID ("+keyID+") from the lockID( "+lockID+" )" );
					j.setBounds(40, 20, 500, 30);
					yes = new JButton("Yes");
					yes.setBounds(400, 50, 70, 30);
					yes.addActionListener(this);
					window3.add(yes);
					
					window3.add(j);
					window3.repaint();
					window3.setVisible(true);
					
					kID = keyID;
					lID = lockID;
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} 
		else if(e.getSource() == yes){
			window3.repaint();
			window3.setVisible(false);
			window2.repaint();
			window2.setVisible(false);
			try {
				data.deleteKey(kID, lID);
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		}
		else if (e.getSource() == win2Asign) {
			String kI = (String) swipeCard.getSelectedItem();
			String lI = (String) lockForSC.getSelectedItem();
			int keyID = Integer.parseInt(kI);
			Scanner scan = new Scanner(lI);
			scan.next();
			int lockID = scan.nextInt();
			try {
				data.addKey(keyID, lockID);
				thirdWindow();
				JLabel j = new JLabel("New key ( "+ keyID+" ) has benn added to the lock ( "+lockID+" )." );
				j.setBounds(40, 20, 500, 30);
				window2.setVisible(false);
				window3.add(j);
				window3.repaint();
				window3.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == addBuilding) {
			String choose = (String) addBuilding.getSelectedItem();
			if (choose.equals("WING TECH CENTER")) {
				if (addRoom != null) {
					window2.remove(addRoom);
				}
				if (win2Add != null) {
					window2.remove(win2Add);
				}
				String[] b = { "101", "102", "103", "104" };
				addRoom = new JComboBox<String>(b);
				addRoom.addActionListener(this);
				addRoom.setBounds(80, 130, 250, 30);
				window2.add(addRoom);

				win2Add = new JButton("Add");
				win2Add.addActionListener(this);
				win2Add.setBounds(80, 250, 250, 30);
				win2Add.setForeground(white);
				win2Add.setBackground(darkBlue);
				win2Add.setOpaque(true);
				win2Add.setBorderPainted(false);
				window2.add(win2Add);

				window2.repaint();
				window2.setVisible(true);
			} else if (choose.equals("CENTENNIAL")) {
				if (addRoom != null) {
					window2.remove(addRoom);
				}
				if (win2Add != null) {
					window2.remove(win2Add);
				}
				String[] b = { "101", "102", "103", "201", "202", "203" };
				addRoom = new JComboBox<String>(b);
				addRoom.addActionListener(this);
				addRoom.setBounds(80, 130, 250, 30);
				window2.add(addRoom);

				win2Add = new JButton("Add");
				win2Add.addActionListener(this);
				win2Add.setBounds(80, 250, 250, 30);
				win2Add.setForeground(white);
				win2Add.setBackground(darkBlue);
				win2Add.setOpaque(true);
				win2Add.setBorderPainted(false);
				window2.add(win2Add);

				window2.repaint();
				window2.setVisible(true);
			} else {
				if (addRoom != null) {
					addRoom.setVisible(false);
				}
				if (win2Add != null) {
					win2Add.setVisible(false);
				}

				window2.repaint();
				window2.setVisible(true);
			}
		} else if (e.getSource() == win2Add) {
			try {
				data.addNewLock(Integer.parseInt((String) addRoom.getSelectedItem()),
						(String) addBuilding.getSelectedItem());
				thirdWindow();
				JLabel j = new JLabel("New key has benn added to : "+ ((String)addRoom.getSelectedItem()) +" "+((String)addBuilding.getSelectedItem()));
				j.setBounds(40, 20, 500, 30);
				window2.setVisible(false);
				window3.add(j);
				window3.repaint();
				window3.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// to do print message
		} else if (e.getSource() == win2AddKey) {
			if (((String) addKey.getSelectedItem()).equals("Select a lock to add a new key")) {
				thirdWindow();
				JLabel j = new JLabel("You should select a lock.");
				j.setForeground(Color.red);
				j.setBounds(40, 20, 500, 30);
				window3.add(j);
				window3.repaint();
				window3.setVisible(true);
			} else {
				int num = Integer.parseInt(((String) addKey.getSelectedItem()));
				boolean type = false;
				if (((String) keyType.getSelectedItem()).equals("Key")) {
					type = true;
				}
				try {
					data.addNewKey(num, type);
					thirdWindow();
					JLabel j = new JLabel("New key has benn added to : the lock "+num );
					j.setBounds(40, 20, 500, 30);
					window2.setVisible(false);
					window3.add(j);
					window3.repaint();
					window3.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} 
		
		else if (e.getSource() == allKey) {
			if (allKey.getSelectedIndex() != 0) {
				if (lockForKeys != null) {
					window2.remove(lockForKeys);
				}
				if (win2Design != null) {
					window2.remove(win2Design);
				}
				String sc = (String) allKey.getSelectedItem();
				int keyId = Integer.parseInt(sc);
				HashSet<Lock1> locks = null;
				try {
					locks = data.searchLocksOpenedByGivenKey(keyId);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				Lock1 temp = null;
				Iterator<Lock1> iter = locks.iterator();
				String[] s = new String[locks.size()];
				int i = 0;
				while (iter.hasNext()) {
					temp = iter.next();
					s[i] = "LockID: " + temp.getID();
					i++;
				}
				lockForKeys = new JComboBox<String>(s);
				lockForKeys.addActionListener(this);
				lockForKeys.setBounds(80, 150, 250, 30);
				window2.add(lockForKeys);

				win2Design = new JButton("Delete");
				win2Design.addActionListener(this);
				win2Design.setBounds(80, 250, 250, 30);
				win2Design.setForeground(white);
				win2Design.setBackground(darkBlue);
				win2Design.setOpaque(true);
				win2Design.setBorderPainted(false);
				window2.add(win2Design);

				window2.repaint();
				window2.setVisible(true);
				}
		}
		else if (e.getSource() == swipeCard) {
			if (swipeCard.getSelectedIndex() != 0) {
				if (lockForSC != null) {
					window2.remove(lockForSC);
				}
				if (win2Asign != null) {
					window2.remove(win2Asign);
				}
				String sc = (String) swipeCard.getSelectedItem();
				int keyId = Integer.parseInt(sc);
				HashSet<Lock1> locks = null;
				try {
					locks = data.searchLocksNotOpenedByGivenKey(keyId);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				Lock1 temp = null;
				Iterator<Lock1> iter = locks.iterator();
				String[] s = new String[locks.size()];
				int i = 0;
				while (iter.hasNext()) {
					temp = iter.next();
					s[i] = "LockID: " + temp.getID();
					i++;
				}
				lockForSC = new JComboBox<String>(s);
				lockForSC.addActionListener(this);
				lockForSC.setBounds(80, 150, 250, 30);
				window2.add(lockForSC);

				win2Asign = new JButton("Add");
				win2Asign.addActionListener(this);
				win2Asign.setBounds(80, 250, 250, 30);
				win2Asign.setForeground(white);
				win2Asign.setBackground(darkBlue);
				win2Asign.setOpaque(true);
				win2Asign.setBorderPainted(false);
				window2.add(win2Asign);

				window2.repaint();
				window2.setVisible(true);
			} else {
				if (lockForSC != null) {
					window2.remove(lockForSC);
				}
				if (win2Asign != null) {
					window2.remove(win2Asign);
				}
				window2.repaint();
				window2.setVisible(true);
			}
		}
		window.repaint();
	}

	public String[] getSwipeCards() {
		int i = 0;
		HashSet<Key1> k = data.getAllKeys();
		Key1 temp = null;
		Iterator<Key1> iter = k.iterator();
		while (iter.hasNext()) {
			temp = iter.next();
			if (temp.getType() == false) {
				i++;
			}
		}
		String[] s = new String[i + 1];
		s[0] = "Select swipe cards";
		int j = 1;

		iter = k.iterator();
		while (iter.hasNext()) {
			temp = iter.next();
			if (temp.getType() == false) {
				s[j] = "" + temp.getID();
				j++;
			}
		}
		return s;
	}

}
