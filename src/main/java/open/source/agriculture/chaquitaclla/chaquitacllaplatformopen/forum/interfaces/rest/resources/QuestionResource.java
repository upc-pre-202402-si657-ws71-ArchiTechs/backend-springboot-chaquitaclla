package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources;


import java.util.Date;

public record QuestionResource(Long id, Long categoryId, Long userId, String questionText, Date createdDate) {
}
