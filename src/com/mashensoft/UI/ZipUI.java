package com.mashensoft.UI;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import com.mashensoft.zip.Unzip;
import com.mashensoft.zip.Zip;
/**
 * ��ѹ�û�������
 * @author zongxing
 *
 */
public class ZipUI extends JFrame {
	File sourceFile = null;

	public ZipUI() {
		/*
		 * ���ô��ڵĴ�С��������Ϊ��λ�� 4�������ֱ��ǣ�����Ļ��ߣ��ϱߵľ��룻���ڵĿ�Ⱥ͸߶�
		 */
		this.setTitle("�����ѹ������");
		this.setBounds(1000, 500, 500, 500);

		this.setLayout(new GridLayout(2, 1));
		Panel p1 = new Panel();

		JLabel label = new JLabel("�ļ�·��");
		JTextField textField = new JTextField(20);

		JButton choiceButton = new JButton("ѡ���ļ�");

		choiceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(".");
				fileChooser.setDialogTitle("�����ļ�ѡ����");
				// fileChooser.setMultiSelectionEnabled(tr
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fileChooser.showOpenDialog(choiceButton);
				if (result == JFileChooser.CANCEL_OPTION || result == JFileChooser.ERROR_OPTION) {
					System.out.println("δѡ���ļ�");
					return;
				}
				sourceFile = fileChooser.getSelectedFile();
				System.out.println(sourceFile.getName());
				textField.setText(sourceFile.getAbsolutePath());
			}
		});
		JButton button = new JButton("ѹ��");
		p1.add(label);
		p1.add(textField);
		p1.add(choiceButton);
		p1.add(button);
		this.add(p1);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (sourceFile == null || sourceFile.getName() == null || sourceFile.getName().equals("")) {
					JOptionPane.showMessageDialog((Component) e.getSource(), "��ѡ��Ҫѹ�����ļ����ļ���", "��ʾ",
							JOptionPane.OK_CANCEL_OPTION);
					return;
				}
				try {
					if (sourceFile.isDirectory()) {
						// ѹ���ļ���
						Zip.recursion(textField.getText());
					} else if (sourceFile.isFile()) {
						// ѹ���ļ�
						Zip.zipFile(textField.getText());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		Panel p2 = new Panel();
		JLabel unzipLabel = new JLabel("��ѹ��");
		JTextField unzipTextField = new JTextField(20);
		JButton unzipChoiceButton = new JButton("ѡ���ļ�");
		JButton unzipButton = new JButton("��ѹ");

		unzipChoiceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(".");
				fileChooser.setDialogTitle("�����ļ�ѡ����");
				// fileChooser.setMultiSelectionEnabled(tr
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = fileChooser.showOpenDialog(choiceButton);
				if (result == JFileChooser.CANCEL_OPTION || result == JFileChooser.ERROR_OPTION) {
					System.out.println("δѡ���ļ�");
					return;
				}
				File file = fileChooser.getSelectedFile();
				System.out.println(file.getName());
				unzipTextField.setText(file.getAbsolutePath());
				fileChooser.setAcceptAllFileFilterUsed(true);
				fileChooser.addChoosableFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public boolean accept(File f) {
						return f.getName().endsWith("zip");
					}
				});

			}
		});
		unzipButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String filePath = unzipTextField.getText();
				if (filePath == null || filePath.equals("") || !filePath.endsWith("zip")) {
					System.out.println("�ļ���ʽ������zip��ʽ");
					JOptionPane.showMessageDialog((Component) e.getSource(), "�ļ���ʽ������zip��ʽ", "��ʾ",
							JOptionPane.OK_CANCEL_OPTION);
					return;
				}

				try {
					Unzip.unzipFolder(unzipTextField.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		p2.add(unzipLabel);
		p2.add(unzipTextField);
		p2.add(unzipChoiceButton);
		p2.add(unzipButton);
		this.add(p2);
		this.setVisible(true);// �����Ƿ�ɼ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		ZipUI ui = new ZipUI();
	}
}
