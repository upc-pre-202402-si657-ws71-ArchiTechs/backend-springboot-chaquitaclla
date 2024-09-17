package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Answer;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllAnswersByQuestionIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllAnswersQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAnswerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AnswerQueryService {
    List<Answer> handle(GetAllAnswersByQuestionIdQuery query);
    List<Answer> handle(GetAllAnswersQuery query);
    Optional<Answer> handle(GetAnswerByIdQuery query);
}
