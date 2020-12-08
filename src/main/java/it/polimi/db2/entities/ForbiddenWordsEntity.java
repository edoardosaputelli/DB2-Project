package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "forbidden_words", schema = "db2_project_schema")
@NamedQuery(name = "ForbiddenWordsRetrieval", query = "SELECT i from ForbiddenWordsEntity i")
public class ForbiddenWordsEntity implements Serializable {
    @Id
    @Column(name = "forbidden_word", nullable = false, length = 45)
    private String forbiddenWord;

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
