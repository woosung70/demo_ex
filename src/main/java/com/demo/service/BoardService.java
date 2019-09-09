package com.demo.service;

import java.util.List;

import com.demo.domain.Board;

public interface BoardService {
	public List<Board> listBoard();
	public void insertBoard(Board dto);
	public Board viewBoard(int idx);
	public void updateBoard(Board dto);
	public void deleteBoard(int idx);
}

