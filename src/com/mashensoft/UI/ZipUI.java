package com.mashensoft.UI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mashensoft.zip.Unzip;
import com.mashensoft.zip.Zip;

public class ZipUI extends JFrame {
	public ZipUI() {
		/*
		 * 设置窗口的大小（以像素为单位） 4个参数分别是：离屏幕左边，上边的距离；窗口的宽度和高度
		 */
		this.setTitle("码神解压缩工具");
		this.setBounds(1000, 500, 500, 500);
		
		
		this.setLayout(new GridLayout(2, 1));
		Panel p1 = new Panel();
		
		JLabel label = new JLabel("文件路径");
		JTextField textField  = new JTextField(20);
		
		
		JButton choiceButton = new JButton("选择文件");
		
		choiceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(".");
				fileChooser.setDialogTitle("码神文件选择器");
				//fileChooser.setMultiSelectionEnabled(tr
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(choiceButton);
				File file = fileChooser.getSelectedFile();
				System.out.println(file.getName());
				textField.setText(file.getAbsolutePath());
			}
		});
		JButton button = new JButton("压缩");
		p1.add(label);
		p1.add(textField);
		p1.add(choiceButton);
		p1.add(button);
		this.add(p1);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Zip.recursion(textField.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		Panel p2 = new Panel();
		JLabel unzipLabel = new JLabel("解压缩");
		JTextField unzipTextField = new JTextField(20);
		JButton unzipChoiceButton = new JButton("选择文件");
		JButton unzipButton = new JButton("解压");
		
		unzipChoiceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(".");
				fileChooser.setDialogTitle("码神文件选择器");
				//fileChooser.setMultiSelectionEnabled(tr
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(choiceButton);
				File file = fileChooser.getSelectedFile();
				System.out.println(file.getName());
				unzipTextField.setText(file.getAbsolutePath());
			}
		});
		unzipButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
		this.setVisible(true);// 设置是否可见
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		ZipUI ui =new ZipUI();
	}
}
