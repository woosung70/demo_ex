package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.domain.Board;
import com.demo.service.BoardService;


@RestController // @Controller + @ResponseBody 묶음 
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService service; 
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("board/index");
		return mav;
	}

	@RequestMapping(value = "/boards", method = RequestMethod.GET)
	public ResponseEntity<List<Board>> getBoardList() {
		ResponseEntity<List<Board>> entity = null;
		try {
			List<Board> list = service.listBoard();
			System.out.println(list);
			entity = new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/boards/{idx}", method = RequestMethod.GET)
	public ResponseEntity<Board> getBoard(@PathVariable int idx) {
		ResponseEntity<Board> entity = null;
		try {
			Board board = service.viewBoard(idx);
			entity = new ResponseEntity<>(board, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/boards", method = RequestMethod.POST)
	public ResponseEntity<String> insertBoard(@RequestBody Board board) {
		ResponseEntity<String> entity = null;
		try {
			service.insertBoard(board);
			//System.out.println(board);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/boards", method = RequestMethod.PUT)
	public ResponseEntity<String> updateBoard(@RequestBody Board board) {
		ResponseEntity<String> entity = null;
		try {
			if(board != null) service.updateBoard(board);
			//System.out.println(board);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value = "/boards/{idx}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBoard(@PathVariable int idx) {
		ResponseEntity<String> entity = null;
		try {
			System.out.println(idx);
			service.deleteBoard(idx);;
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

//	@RequestMapping(value = "/boards", method = RequestMethod.PUT)
//	public Map<String, Object> updateBoard(@RequestBody Board board) {
//		System.out.println(board);
//		if(board != null) service.updateBoard(board);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("result", Boolean.TRUE);
//		return result;
//	}
//
//	@RequestMapping(value = "/boards/{idx}", method = RequestMethod.DELETE)
//	public Map<String, Object> deleteBoard(@PathVariable int idx) {
//		service.deleteBoard(idx);;
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("result", Boolean.TRUE);
//		return result;
//	}
	
}
