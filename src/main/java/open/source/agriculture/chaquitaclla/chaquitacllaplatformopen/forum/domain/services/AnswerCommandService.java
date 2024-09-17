package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Answer;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.DeleteAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.UpdateAnswerCommand;

import java.util.Optional;

public interface AnswerCommandService {
    Long handle(CreateAnswerCommand command);
    Optional<Answer> handle(UpdateAnswerCommand command);
    void handle(DeleteAnswerCommand command);
}
