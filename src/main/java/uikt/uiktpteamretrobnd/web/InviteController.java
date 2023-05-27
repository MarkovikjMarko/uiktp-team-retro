package uikt.uiktpteamretrobnd.web;

import org.springframework.web.bind.annotation.*;
import uikt.uiktpteamretrobnd.model.Invite;
import uikt.uiktpteamretrobnd.service.InviteService;
import uikt.uiktpteamretrobnd.web.dto.InviteDTO;
import uikt.uiktpteamretrobnd.web.requests.InviteRequest;
import uikt.uiktpteamretrobnd.web.response.ApiResponse;
import uikt.uiktpteamretrobnd.web.response.CustomResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invites")
public class InviteController {
    private InviteService inviteService;
    private CustomResponse customResponse;

    public InviteController(InviteService inviteService, CustomResponse customResponse) {
        this.inviteService = inviteService;
        this.customResponse = customResponse;
    }

    @GetMapping
    public ApiResponse<List<Invite>> index() {
        List<Invite> invites = inviteService.findAll();
        List<InviteDTO> inviteDTOS = new ArrayList<>();

        for(Invite invite: invites){
            inviteDTOS.add(new InviteDTO(invite));
        }

        return this.customResponse.success(inviteDTOS);
    }

    @GetMapping("/{id}")
    public ApiResponse<Invite> show(@PathVariable("id") Long id) {
        Optional<Invite> invite = inviteService.find(id);

        if (invite.isPresent()) {
            return this.customResponse.success(new InviteDTO(invite.get()));
        } else {
            return this.customResponse.notFound(invite);
        }
    }

    @PostMapping
    public ApiResponse<Object> create(@ModelAttribute InviteRequest inviteRequest) {
        if(inviteRequest.getTeamId() != null){
            List<InviteDTO> inviteDTOS = this.inviteService.createTeamInvites(inviteRequest);

            return this.customResponse.success(inviteDTOS);
        }

        Invite invite = inviteService.createSingleInvite(inviteRequest);

        return this.customResponse.created(new InviteDTO(invite));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable("id") Long id) {
        boolean deleted = inviteService.delete(id);

        if (deleted) {
            return customResponse.deleted();
        } else {
            return this.customResponse.notFound(null);
        }
    }
}
