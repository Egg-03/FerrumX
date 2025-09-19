package org.ferrumx.entity.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

@Data
@RequiredArgsConstructor
public class User {

    @Nullable
    private String userName;

    @Nullable
    private String userHome;

    @Nullable
    private String userDirectory;
}
