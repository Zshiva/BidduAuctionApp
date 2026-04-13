package practice.projects.platform.security;

import io.micronaut.security.annotation.Secured;

/**
 * Custom security annotations for role-based access control
 */
public class SecurityAnnotations {

    /**
     * Annotation for BIDDER role access
     */
    @Secured("BIDDER")
    public @interface RequireBidder {
    }

    /**
     * Annotation for SELLER role access
     */
    @Secured("SELLER")
    public @interface RequireSeller {
    }

    /**
     * Annotation for ADMIN role access
     */
    @Secured("ADMIN")
    public @interface RequireAdmin {
    }

    /**
     * Annotation for authenticated users (any role)
     */
    @Secured({"BIDDER", "SELLER", "ADMIN"})
    public @interface RequireAuthenticated {
    }
}

