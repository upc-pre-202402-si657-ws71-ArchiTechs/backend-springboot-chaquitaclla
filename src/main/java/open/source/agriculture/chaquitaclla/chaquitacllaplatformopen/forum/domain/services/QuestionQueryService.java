package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllQuestionsByUserIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllQuestionsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetQuestionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface QuestionQueryService {
    List<Question> handle(GetAllQuestionsQuery query);
    List<Question> handle(GetAllQuestionsByUserIdQuery query);
    Optional<Question> handle(GetQuestionByIdQuery query);
}
