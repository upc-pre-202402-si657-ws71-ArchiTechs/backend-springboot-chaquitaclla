package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.UpdateAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.UpdateAnswerResource;

public class UpdateAnswerCommandFromResourceAssembler {
    public static UpdateAnswerCommand toCommandFromResource(Long answerId, UpdateAnswerResource resource){
        return new UpdateAnswerCommand(
                answerId,
                resource.answerText()
        );
    }
}
