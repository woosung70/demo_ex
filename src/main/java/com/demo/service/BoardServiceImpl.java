package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Board;
import com.demo.mapper.BoardMapper;

@Service //service bean으로 등록
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<Board> listBoard() {
		return mapper.list(); 
	}

	@Override
	public void insertBoard(Board dto) {
		mapper.insert(dto); 
	}

	@Override
	public Board viewBoard(int idx) {
		return mapper.view(idx); 
	}

	@Override
	public void updateBoard(Board dto) {
		mapper.update(dto); 
	}

	@Override
	public void deleteBoard(int idx) {
		mapper.delete(idx);
	}

}










