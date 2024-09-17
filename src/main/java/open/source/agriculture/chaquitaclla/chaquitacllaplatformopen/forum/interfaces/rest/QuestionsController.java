package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.DeleteQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllQuestionsByUserIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllQuestionsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetQuestionByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.QuestionCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.QuestionQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.CreateQuestionResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.QuestionResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.UpdateQuestionResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform.CreateQuestionCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform.QuestionResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform.UpdateQuestionCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Question Controller.
 * <p>
 * This class is the entry point for all the REST API calls related to questions.
 * It is responsible for handling the requests and delegating the processing to the appropriate services.
 * It also transforms the data from the request to the appropriate commands and vice versa.
 * <ul>
 *     <li>POST /api/v1/forum/questions</li>
 *     <li>GET /api/v1/forum/questions/{questionId}</li>
 *     <li>GET /api/v1/forum/questions</li>
 *     <li>PUT /api/v1/forum/questions/{questionId}</li>
 *     <li>DELETE /api/v1/forum/questions/{questionId}</li>
 *     <li>GET /api/v1/forum/questions/user/{userId}</li>
 * </ul>
 * </p>
 *
 *
 */
@RestController
@RequestMapping(value = "/api/v1/forum/questions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Questions", description = "Questions Management Endpoints")
public class QuestionsController {
    private final QuestionCommandService questionCommandService;
    private final QuestionQueryService questionQueryService;

    public QuestionsController(QuestionCommandService questionCommandService, QuestionQueryService questionQueryService) {
        this.questionCommandService = questionCommandService;
        this.questionQueryService = questionQueryService;
    }

    @PostMapping()
    public ResponseEntity<QuestionResource> createQuestion(@RequestBody CreateQuestionResource resource) {
        var createQuestionCommand = CreateQuestionCommandFromResourceAssembler.toCommandFromResource(resource);
        var questionId = questionCommandService.handle(createQuestionCommand);
        if(questionId == 0L) return ResponseEntity.badRequest().build();
        var getQuestionByIdQuery = new GetQuestionByIdQuery(questionId);
        var question = questionQueryService.handle(getQuestionByIdQuery);
        if(question.isEmpty()) return ResponseEntity.badRequest().build();
        var questionResource = QuestionResourceFromEntityAssembler.toResourceFromEntity(question.get());
        return new ResponseEntity<>(questionResource, HttpStatus.CREATED);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionResource> updateQuestion(@PathVariable Long questionId, @RequestBody UpdateQuestionResource updateQuestionResource){
        var updateQuestionCommand = UpdateQuestionCommandFromResourceAssembler.toCommandFromResource(questionId, updateQuestionResource);
        var updatedQuestion = questionCommandService.handle(updateQuestionCommand);
        if(updatedQuestion.isEmpty()) return ResponseEntity.badRequest().build();
        var questionResource = QuestionResourceFromEntityAssembler.toResourceFromEntity(updatedQuestion.get());
        return ResponseEntity.ok(questionResource);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable Long questionId){
        var deleteQuestionCommand = new DeleteQuestionCommand(questionId);
        questionCommandService.handle(deleteQuestionCommand);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<QuestionResource>> getAllQuestions(){
        var getAllQuestionsQuery = new GetAllQuestionsQuery();
        var questions = questionQueryService.handle(getAllQuestionsQuery);
        var questionResource = questions.stream()
                .map(QuestionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(questionResource);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResource> getQuestionById(@PathVariable Long questionId){
        var getQuestionByIdQuery = new GetQuestionByIdQuery(questionId);
        var question = questionQueryService.handle(getQuestionByIdQuery);
        if(question.isEmpty()) return ResponseEntity.badRequest().build();
        var questionResource = QuestionResourceFromEntityAssembler.toResourceFromEntity(question.get());
        return ResponseEntity.ok(questionResource);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuestionResource>> getAllQuestionsByUserId(@PathVariable Long userId){
        var IdUser = new UserId(userId);
        var getQuestionsByUserIdQuery = new GetAllQuestionsByUserIdQuery(IdUser);
        var questions = questionQueryService.handle(getQuestionsByUserIdQuery);
        var questionResource = questions.stream()
                .map(QuestionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(questionResource);
    }
}
