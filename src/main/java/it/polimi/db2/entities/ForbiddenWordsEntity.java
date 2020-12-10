package it.polimi.db2.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "forbidden_words", schema = "db2_project_schema")
@NamedQueries({@NamedQuery(name = "ForbiddenWordsCount", query = "SELECT count(i) from ForbiddenWordsEntity i"),
@NamedQuery(name = "ForbiddenWordN", query ="SELECT i FROM ForbiddenWordsEntity i WHERE i.forbidden_id = ?1")})

public class ForbiddenWordsEntity implements Serializable {
    @Id
    @Column(name = "forbidden_id", nullable = false, length = 45)
    private int forbidden_id;

    private String forbiddenWord;

    public String getForbiddenWord() {
        return forbiddenWord;
    }

    public void setForbiddenWord(String forbiddenWord) {
        this.forbiddenWord = forbiddenWord;
    }

    public int getForbidden_id() {
        return forbidden_id;
    }

    public void setForbidden_id(int forbidden_id) {
        this.forbidden_id = forbidden_id;
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
