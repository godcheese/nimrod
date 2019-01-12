package com.gioov.nimrod.common.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
public class SimpleUser implements SimpleUserDetails, CredentialsContainer {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private static final Log logger = LogFactory.getLog(User.class);

    private Long id;
    private String password;
    private final String username;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    // ~ Constructors
    // ===================================================================================================

    public SimpleUser(Long id, String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
        this(id, username, password, true, true, true, true, authorities);
    }

    public SimpleUser(Long id, String username, String password, boolean enabled,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

        boolean b = (id == null || (username == null) || "".equals(username));
        if (b || password == null) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }

        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(
            Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per
        // UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(
                new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority,
                    "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>,
            Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to
            // the set.
            // If the authority is null, it is a custom authority and should precede
            // others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    /**
     * Returns {@code true} if the supplied object is a {@code User} instance with the
     * same {@code username} value.
     * <p>
     * In other words, the objects are equal if they have the same username, representing
     * the same principal.
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof SimpleUser) {
            return id.equals(((SimpleUser) rhs).id);
        }
        return false;
    }

    /**
     * Returns the hashcode of the {@code username}.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Id: ").append(this.id).append("; ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired)
                .append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

        if (!authorities.isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (GrantedAuthority auth : authorities) {
                if (!first) {
                    sb.append(",");
                }
                first = false;

                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }


    public static SimpleUserBuilder withId(Long id) {
        return builder().id(id);
    }

    /**
     * Creates a UserBuilder
     *
     * @return the UserBuilder
     */
    public static SimpleUserBuilder builder() {
        return new SimpleUserBuilder();
    }

    @Deprecated
    public static SimpleUserBuilder withDefaultPasswordEncoder() {
        logger.warn("User.withDefaultPasswordEncoder() is considered unsafe for production and is only intended for sample applications.");
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return builder().passwordEncoder(encoder::encode);
    }

    public static SimpleUserBuilder withSimpleUserDetails(SimpleUserDetails simpleUserDetails) {
        return withId(simpleUserDetails.getId()).username(simpleUserDetails.getUsername())
                .password(simpleUserDetails.getPassword())
                .accountExpired(!simpleUserDetails.isAccountNonExpired())
                .accountLocked(!simpleUserDetails.isAccountNonLocked())
                .authorities(simpleUserDetails.getAuthorities())
                .credentialsExpired(!simpleUserDetails.isCredentialsNonExpired())
                .disabled(!simpleUserDetails.isEnabled());
    }

    /**
     * Builds the user to be added. At minimum the username, password, and authorities
     * should provided. The remaining attributes have reasonable defaults.
     */
    public static class SimpleUserBuilder {
        private Long id;
        private String username;
        private String password;
        private List<GrantedAuthority> authorities;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private Function<String, String> passwordEncoder = password -> password;

        /**
         * Creates a new instance
         */
        private SimpleUserBuilder() {
        }

        /**
         * Populates the username. This attribute is required.
         *
         * @param id the id. Cannot be null.
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public SimpleUserBuilder id(Long id) {
            Assert.notNull(id, "id cannot be null");
            this.id = id;
            return this;
        }

        /**
         * Populates the username. This attribute is required.
         *
         * @param username the username. Cannot be null.
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public SimpleUserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        /**
         * Populates the password. This attribute is required.
         *
         * @param password the password. Cannot be null.
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public SimpleUserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        /**
         * Encodes the current password (if non-null) and any future passwords supplied
         * to {@link #password(String)}.
         *
         * @param encoder the encoder to use
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public SimpleUserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        /**
         * Populates the roles. This method is a shortcut for calling
         * {@link #authorities(String...)}, but automatically prefixes each entry with
         * "ROLE_". This means the following:
         *
         * <code>
         * builder.roles("USER","ADMIN");
         * </code>
         * <p>
         * is equivalent to
         *
         * <code>
         * builder.authorities("ROLE_USER","ROLE_ADMIN");
         * </code>
         *
         * <p>
         * This attribute is required, but can also be populated with
         * {@link #authorities(String...)}.
         * </p>
         *
         * @param roles the roles for this user (i.e. USER, ADMIN, etc). Cannot be null,
         *              contain null values or start with "ROLE_"
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public SimpleUserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>(
                    roles.length);
            for (String role : roles) {
                Assert.isTrue(!role.startsWith("ROLE_"), role
                        + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
            return authorities(authorities);
        }

        /**
         * Populates the authorities. This attribute is required.
         *
         * @param authorities the authorities for this user. Cannot be null, or contain
         *                    null values
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         * @see #roles(String...)
         */
        public SimpleUserBuilder authorities(GrantedAuthority... authorities) {
            return authorities(Arrays.asList(authorities));
        }

        /**
         * Populates the authorities. This attribute is required.
         *
         * @param authorities the authorities for this user. Cannot be null, or contain
         *                    null values
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         * @see #roles(String...)
         */
        public SimpleUserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList<>(authorities);
            return this;
        }

        /**
         * Populates the authorities. This attribute is required.
         *
         * @param authorities the authorities for this user (i.e. ROLE_USER, ROLE_ADMIN,
         *                    etc). Cannot be null, or contain null values
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         * @see #roles(String...)
         */
        public SimpleUserBuilder authorities(String... authorities) {
            return authorities(AuthorityUtils.createAuthorityList(authorities));
        }

        /**
         * Defines if the account is expired or not. Default is false.
         *
         * @param accountExpired true if the account is expired, false otherwise
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public SimpleUserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        /**
         * Defines if the account is locked or not. Default is false.
         *
         * @param accountLocked true if the account is locked, false otherwise
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public SimpleUserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        /**
         * Defines if the credentials are expired or not. Default is false.
         *
         * @param credentialsExpired true if the credentials are expired, false otherwise
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public SimpleUserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        /**
         * Defines if the account is disabled or not. Default is false.
         *
         * @param disabled true if the account is disabled, false otherwise
         * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
         * additional attributes for this user)
         */
        public SimpleUserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public SimpleUserDetails build() {
            String encodedPassword = this.passwordEncoder.apply(password);
            return new SimpleUser(id, username, encodedPassword, !disabled, !accountExpired,
                    !credentialsExpired, !accountLocked, authorities);
        }
    }
}

