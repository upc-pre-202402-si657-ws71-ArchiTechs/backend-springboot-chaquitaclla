package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Answer;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.DeleteQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.UpdateQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities.Category;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.QuestionCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.AnswerRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.CategoryRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionCommandServiceImpl implements QuestionCommandService {

    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    private final AnswerRepository answerRepository;
    public QuestionCommandServiceImpl(QuestionRepository questionRepository, CategoryRepository categoryRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
        this.answerRepository = answerRepository;
    }


    @Override
    public Long handle(CreateQuestionCommand command) {
        if(questionRepository.existsByQuestionText(command.questionText()))
            throw new IllegalArgumentException("Question already exists");
        Optional<Category> optionalCategory = categoryRepository.findById(command.categoryId());
        if(optionalCategory.isEmpty())
            throw new IllegalArgumentException("Category does not exist");
        Category category = optionalCategory.get();
        var question = new Question(command, category);
        try {
            questionRepository.save(question);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving question: " + e.getMessage());
        }

        return question.getId();
    }

    @Override
    public Optional<Question> handle(UpdateQuestionCommand command) {
        if(!questionRepository.existsById(command.questionId()))
            throw new IllegalArgumentException("Question does not exist");
        Optional<Category> optionalCategory = categoryRepository.findById(command.categoryId());
        if(optionalCategory.isEmpty())
            throw new IllegalArgumentException("Category does not exist");
        Category category = optionalCategory.get();
        var questionToUpdate = questionRepository.findById(command.questionId()).get();
        try {
            var updateQuestion = questionRepository.save(questionToUpdate.updateInformation(category, command.questionText()));
            return Optional.of(updateQuestion);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating question: " + e.getMessage());
        }

    }

    @Override
    public void handle(DeleteQuestionCommand command) {
        if(!questionRepository.existsById(command.questionId())){
            throw new IllegalArgumentException("Question does not exist");
        }
        try {
            List<Answer> answers = answerRepository.findByQuestionId(command.questionId());

            answerRepository.deleteAll(answers);

            questionRepository.deleteById(command.questionId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting question: " + e.getMessage());
        }

    }
}
