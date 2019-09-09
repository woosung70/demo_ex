package com.demo.mapper;

import java.util.List;

import com.demo.domain.Board;

//@Repository
public interface BoardMapper {
	public List<Board> list();
	public Board view(int idx);
	public void insert(Board dto);
	public void update(Board dto);
	public void delete(int idx);
}
