package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities.Category;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
public class Question extends AuditableAbstractAggregateRoot<Question> {

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JoinColumn(name = "user_id")
    private UserId userId;

    @NotNull
    private String questionText;


    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Question() {
        this.category = new Category();
        this.userId = new UserId();
        this.questionText = Strings.EMPTY;
    }

    public Question(Category category, Long userId, String questionText) {
        this();
        this.category = category;
        this.userId = new UserId(userId);
        this.questionText = questionText;
    }

    public Question(CreateQuestionCommand command, Category category) {
        this();
        this.category = category;
        this.userId = new UserId(command.userId());
        this.questionText = command.questionText();
    }

    public Question updateInformation(Category category, String question) {
        this.category = category;
        this.questionText = question;
        return this;
    }
}
