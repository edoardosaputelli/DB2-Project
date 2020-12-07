package it.polimi.db2.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "forbidden_words", schema = "db2_project_schema")
public class ForbiddenWordsEntity implements Serializable {
    private String forbiddenWord;

    @Id
    @Column(name = "forbidden_word", nullable = false, length = 45)
    public String getForbiddenWord() {
        return forbiddenWord;
    }

    public void setForbiddenWord(String forbiddenWord) {
        this.forbiddenWord = forbiddenWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForbiddenWordsEntity that = (ForbiddenWordsEntity) o;

        if (forbiddenWord != null ? !forbiddenWord.equals(that.forbiddenWord) : that.forbiddenWord != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return forbiddenWord != null ? forbiddenWord.hashCode() : 0;
    }
}
