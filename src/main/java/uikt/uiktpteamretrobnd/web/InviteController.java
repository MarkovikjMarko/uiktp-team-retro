package uikt.uiktpteamretrobnd.web;

import org.springframework.web.bind.annotation.*;
import uikt.uiktpteamretrobnd.model.Invite;
import uikt.uiktpteamretrobnd.service.InviteService;
import uikt.uiktpteamretrobnd.web.dto.InviteDTO;
import uikt.uiktpteamretrobnd.web.requests.InviteRequest;
import uikt.uiktpteamretrobnd.web.response.ApiResponse;
import uikt.uiktpteamretrobnd.web.response.CustomResponse;

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

        return this.customResponse.success(invites);
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
    public ApiResponse<Invite> create(@ModelAttribute InviteRequest inviteRequest) {
        Invite invite = inviteService.create(inviteRequest);

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
