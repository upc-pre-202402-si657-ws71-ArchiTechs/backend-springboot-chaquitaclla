package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
public class Answer extends AuditableAbstractAggregateRoot<Answer>{

    @JoinColumn(name = "user_id")
    private UserId userId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column
    private String answerText;

    public Answer(){
        this.userId = new UserId();
        this.question = new Question();
        this.answerText = Strings.EMPTY;
    }

    public Answer(Long userId, Question question, String answerText){
        this();
        this.userId = new UserId(userId);
        this.question = question;
        this.answerText = answerText;
    }
    public Answer(CreateAnswerCommand command, Question question){
        this();
        this.userId = new UserId(command.userId());
        this.question = question;
        this.answerText = command.answerText();
    }
    public Answer updateInformation(String answer){
        this.answerText = answer;
        return this;
    }
}
