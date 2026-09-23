package dev.engripaye.backend.business;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final AppUserRepository users;
    private final MembershipRepository membership;

    public AppUser currentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UUID id)) throw new NotFoundException("Authenticated User not found");
        return users.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Membership currentMembership(){
        return membership.findFirstByUser(currentUser()).orElseThrow(() -> new NotFoundException("No business membership found "));
    }




}
