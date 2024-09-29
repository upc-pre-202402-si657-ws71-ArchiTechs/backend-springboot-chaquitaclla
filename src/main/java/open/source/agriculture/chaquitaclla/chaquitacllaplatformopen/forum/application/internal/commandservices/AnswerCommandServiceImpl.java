package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Answer;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.DeleteAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.UpdateAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.AnswerCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.AnswerRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerCommandServiceImpl implements AnswerCommandService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    public AnswerCommandServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }


    @Override
    public Long handle(CreateAnswerCommand command) {
        if (answerRepository.existsByAnswerTextAndQuestionId(command.answerText(), command.questionId()))
            throw new IllegalArgumentException("Answer with same answer text and questionId already exists");
        Optional<Question> optionalQuestion = questionRepository.findById(command.questionId());
        if(optionalQuestion.isEmpty())
            throw new IllegalArgumentException("Question does not exist");
        Question question = optionalQuestion.get();
        Answer answer = new Answer(command, question);
        try {
            answerRepository.save(answer);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while saving answer: " + e.getMessage());
        }
        return answer.getId();
    }

    @Override
    public Optional<Answer> handle(UpdateAnswerCommand command) {
        if(!answerRepository.existsById(command.answerId()))
            throw new IllegalArgumentException("Answer does not exist");
        var answerToUpdate = answerRepository.findById(command.answerId()).get();
        try {
            var updateAnswer = answerRepository.save(answerToUpdate.updateInformation(command.answerText()));
            return Optional.of(updateAnswer);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while updating answer: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteAnswerCommand command) {
        if(!answerRepository.existsById(command.answerId()))
            throw new IllegalArgumentException("Answer does not exist");
        try {
            answerRepository.deleteById(command.answerId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting answer: " + e.getMessage());
        }

    }
}
