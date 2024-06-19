package com.example.samuraitravel.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEditForm{
	@NotNull
	private Integer id;

	@NotNull(message = "評価を選択してください。")
	@Range(min = 1, max = 5, message = "評価は１〜５のいずれかを選択してください。")
	private Integer score;

	@NotBlank(message = "コメントを入力してください。")
	@Length(max = 300, message = "コメントは300文字以内で入力してください。")
	private String content;
	
	@NotNull
	private Integer houseId;
	
	@NotNull
	private Integer userId;
	
	@NotBlank
	private String name;
	
	public ReviewEditForm(Integer id, int score, String content) {
		this.id = id;
		this.score = score;
		this.content = content;
	}
}
